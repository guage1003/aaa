package com.jinguizi.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinguizi.bean.FileDetail;
import com.jinguizi.bean.ModelPlan;
import com.jinguizi.bean.PageResult;
import com.jinguizi.config.CheckResult;
import com.jinguizi.config.JwtUtil;
import com.jinguizi.config.Result;
import com.jinguizi.config.ResultCode;
import com.jinguizi.mapper.FileDetailMapper;
import com.jinguizi.mapper.ModelPlanMapper;
import io.jsonwebtoken.Claims;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * @Title: ModelPlanService
 * @Description:
 * @Auther: liupengxiang
 * @Version: 1.0
 * @create 2020/12/23  15:27
 */
@Service
@Transactional
public class ModelPlanService {
    @Resource
    ModelPlanMapper modelPlanMapper;

    @Resource
    FileDetailMapper fileDetailMapper;


    @Resource
    JwtUtil jwtUtil;

    public PageInfo<ModelPlan> findModelPlanByName(PageResult pageResult) throws ParseException {
        //判断最大获客成本和最小的是不是为空，如果为空就代表不进行对获客成本进行查询
        if(pageResult.getMaxNum()!=null && pageResult.getMinNum()!=null){
            //如果不为空判断是否传过来的数据是按大小顺序进行传递，如果是则直接进行查询
            if (pageResult.getMaxNum() < pageResult.getMinNum()) {//如果不是，就把他们的位置换一下
                Double temp = pageResult.getMaxNum();
                pageResult.setMaxNum(pageResult.getMinNum());
                pageResult.setMinNum(temp);
            }
        }

           /* if(pageResult.getEndTime().getTime()<pageResult.getBeginTime().getTime()){
                Date temp=pageResult.getEndTime();
                pageResult.setEndTime(pageResult.getBeginTime());
                pageResult.setBeginTime(temp);
            }*/

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //判断结束时间是不是为空，不为空的话对他进行格式化并存进实体类，否则他查询则不按当天开始，开始时间也是一样
        if (pageResult.getEndTime() != null) {
            String format2 = sdf.format(pageResult.getEndTime());
            pageResult.setEndTime(sdf.parse(format2));
        }
        if (pageResult.getBeginTime() != null) {
            String format1 = sdf.format(pageResult.getBeginTime());
            pageResult.setBeginTime(sdf.parse(format1));
        }
        PageHelper.startPage(pageResult.getPageNum(), pageResult.getPageSize());
        List<ModelPlan> list = modelPlanMapper.findModelPlanByName(pageResult);
        PageInfo<ModelPlan> modelPlanPageInfo = new PageInfo<>(list);
        return modelPlanPageInfo;

    }

    public Result addModelPlan(HttpServletRequest request, MultipartFile test) {
        try {
            //创建集合，用于存储从excel表中取出的数据，进行一次性向数据库中插入信息，可以避免回滚操作
            ArrayList<ModelPlan> modelPlanArrayList = new ArrayList<>();
            //HashSet<ModelPlan> modelPlanArrayList = new HashSet<>();
            String fileName2 = test.getOriginalFilename();
            String[] split = fileName2.split("\\.");
            //文件名是xxx.xx格式的，我们只要前面的，所以进行截取
            String fileName = split[0];

            System.out.println(fileName);

            XSSFWorkbook workbook = new XSSFWorkbook(test.getInputStream());

            //获取表（参数是第几个表）
            XSSFSheet sheet = workbook.getSheetAt(0);
            //获取一行一行的数据
            //获取表中最后一条数据的下标
            int lastRowNum = sheet.getLastRowNum();
            //我们获取token中的userId，通过jwtUtil解析出来，用于存进我们的对象中区分是哪个用户进行存储
            String token = request.getHeader("token");
            CheckResult checkResult = jwtUtil.validateJWT(token);
            Claims claims = jwtUtil.parseJWT(token);
            String id = claims.getId();

            for (int i = 1; i <= lastRowNum; i++) {
                try {
                    XSSFRow row = sheet.getRow(i);
                    ModelPlan modelPlan = new ModelPlan();
                    String modelName;
                    String operatorsLine;
                    Integer dataCount;
                    Integer connectCount;
                    String channel;
                    Integer triggerCount;
                    Integer sendSuccessCount;
                    Double outboundCost = 0.00;
                    Double smsCost = 0.00;
                    Integer registerCount = 0;
                    Date pushTime;
                    String dataType;

                    /*
                        CellType 类型 值
                        CELL_TYPE_NUMERIC 数值型 0
                        CELL_TYPE_STRING 字符串型 1
                        CELL_TYPE_FORMULA 公式型 2
                        CELL_TYPE_BLANK 空值 3
                        CELL_TYPE_BOOLEAN 布尔型 4
                        CELL_TYPE_ERROR 错误 5
                     */

                    if (row.getCell(0).getCellType() == 0) {
                        modelName = ((Double) row.getCell(0).getNumericCellValue()).intValue() + "";
                        modelPlan.setModelName(modelName);
                    }
                    if (row.getCell(0).getCellType() == 1) {
                        modelName = row.getCell(0).getStringCellValue();
                        modelPlan.setModelName(modelName);
                    }
                    if (row.getCell(0).getCellType() == 3) {
                        modelPlan.setModelName("");
                    }

                    if (row.getCell(1).getCellType() == 0) {
                        operatorsLine = ((Double) row.getCell(1).getNumericCellValue()).intValue() + "";
                        if (operatorsLine.contains("建嘉+")) {
                            String[] split1 = operatorsLine.split("\\+");
                            operatorsLine = split1[1];
                        }
                        modelPlan.setOperatorsLine(operatorsLine);
                    }
                    if (row.getCell(1).getCellType() == 1) {
                        operatorsLine = row.getCell(1).getStringCellValue();
                        if (operatorsLine.contains("建嘉+")) {
                            String[] split1 = operatorsLine.split("\\+");
                            operatorsLine = split1[1];

                        }
                        modelPlan.setOperatorsLine(operatorsLine);
                    }
                    if (row.getCell(1).getCellType() == 3) {
                        modelPlan.setOperatorsLine("");
                    }

                    if (row.getCell(2).getCellType() == 0) {
                        dataCount = ((Double) row.getCell(2).getNumericCellValue()).intValue();
                        modelPlan.setDataCount(dataCount);
                    }
                    if (row.getCell(2).getCellType() == 1) {
                        dataCount = Integer.parseInt(row.getCell(2).getStringCellValue());
                        modelPlan.setDataCount(dataCount);
                    }
                    if (row.getCell(2).getCellType() == 3) {
                        modelPlan.setDataCount(0);
                    }

                    if (row.getCell(3).getCellType() == 0) {
                        connectCount = ((Double) row.getCell(3).getNumericCellValue()).intValue();
                        modelPlan.setConnectCount(connectCount);
                    }
                    if (row.getCell(3).getCellType() == 1) {
                        connectCount = Integer.parseInt(row.getCell(3).getStringCellValue());
                        modelPlan.setConnectCount(connectCount);
                    }
                    if (row.getCell(3).getCellType() == 3) {
                        modelPlan.setConnectCount(0);
                    }

                    if (row.getCell(4).getCellType() == 0) {
                        channel = ((Double) row.getCell(4).getNumericCellValue()).intValue() + "";
                        modelPlan.setChannel(channel);
                    }
                    if (row.getCell(4).getCellType() == 1) {
                        channel = row.getCell(4).getStringCellValue();
                        modelPlan.setChannel(channel);
                    }
                    if (row.getCell(4).getCellType() == 3) {
                        modelPlan.setChannel("");
                    }

                    if (row.getCell(5).getCellType() == 0) {
                        triggerCount = ((Double) row.getCell(5).getNumericCellValue()).intValue();
                        modelPlan.setTriggerCount(triggerCount);
                    }
                    if (row.getCell(5).getCellType() == 1) {
                        triggerCount = Integer.parseInt(row.getCell(5).getStringCellValue());
                        modelPlan.setTriggerCount(triggerCount);
                    }
                    if (row.getCell(5).getCellType() == 3) {
                        modelPlan.setTriggerCount(0);
                    }

                    if (row.getCell(6).getCellType() == 0) {
                        sendSuccessCount = ((Double) row.getCell(6).getNumericCellValue()).intValue();
                        modelPlan.setSendSuccessCount(sendSuccessCount);
                    }
                    if (row.getCell(6).getCellType() == 1) {
                        sendSuccessCount = Integer.parseInt(row.getCell(6).getStringCellValue());
                        modelPlan.setSendSuccessCount(sendSuccessCount);
                    }
                    if (row.getCell(6).getCellType() == 3) {
                        modelPlan.setSendSuccessCount(0);
                    }


                    if (row.getCell(7).getCellType() == 0) {
                        outboundCost = row.getCell(7).getNumericCellValue();
                        modelPlan.setOutboundCost(outboundCost);
                    }
                    if (row.getCell(7).getCellType() == 1) {
                        outboundCost = Double.parseDouble(row.getCell(7).getStringCellValue());
                        modelPlan.setOutboundCost(outboundCost);
                    }
                    if (row.getCell(7).getCellType() == 3) {
                        modelPlan.setOutboundCost(0.00);
                    }

                    if (row.getCell(8).getCellType() == 0) {
                        smsCost = row.getCell(8).getNumericCellValue();
                        modelPlan.setSmsCost(smsCost);
                    }
                    if (row.getCell(8).getCellType() == 1) {
                        smsCost = Double.parseDouble(row.getCell(8).getStringCellValue());
                        modelPlan.setSmsCost(smsCost);
                    }
                    if (row.getCell(8).getCellType() == 3) {
                        modelPlan.setSmsCost(0.00);
                    }

                    if (row.getCell(9).getCellType() == 0) {
                        registerCount = ((Double) row.getCell(9).getNumericCellValue()).intValue();
                        modelPlan.setRegisterCount(registerCount.intValue());
                    }
                    if (row.getCell(9).getCellType() == 1) {
                        registerCount = Integer.parseInt(row.getCell(9).getStringCellValue());
                        modelPlan.setRegisterCount(registerCount);
                    }
                    if (row.getCell(9).getCellType() == 3) {
                        modelPlan.setRegisterCount(0);
                    }

                    pushTime = row.getCell(10).getDateCellValue();

                    if (row.getCell(11).getCellType() == 0) {
                        dataType = ((Double) row.getCell(11).getNumericCellValue()).intValue() + "";
                        modelPlan.setDataType(dataType);
                    }
                    if (row.getCell(11).getCellType() == 1) {
                        dataType = row.getCell(11).getStringCellValue();
                        modelPlan.setDataType(dataType);
                    }
                    if (row.getCell(11).getCellType() == 3) {
                        modelPlan.setDataType("");
                    }
                    modelPlan.setFileName(fileName);
                    modelPlan.setUserId(Integer.parseInt(id));
                    modelPlan.setPushTime(pushTime);
                    Date date = new Date();
                    modelPlan.setGroupTime(date);
                    //我们将获客成本初始化为10000
                    String income = "100000.00";

                    if (registerCount > 0) {
                        //获客成本计算方式：(短信成本+外呼成本)/注册量
                        double v = (outboundCost * 1.0 + smsCost * 1.0) / registerCount;
                        income = (double) (Math.round(v * 100)) / 100 + "";
                        if (income.split("\\.")[1].length() < 2) {
                            income += "0";
                        }
                    }
                    modelPlan.setIncome(income);
                    System.out.println(modelPlan.getIncome());
                    int maxId = fileDetailMapper.findMaxId() + 1;
                    modelPlan.setFileId(maxId);
                    modelPlanArrayList.add(modelPlan);
                } catch (Exception e) {
                    e.printStackTrace();
                    return Result.failure(ResultCode.ERROR_UPLOAD, "第" + (i + 1) + "行数据有误");
                }
            }


            /*for(int i=0;i<modelPlanArrayList.size();i++){
                for(int j=i;j<modelPlanArrayList.size();j++){
                    if(modelPlanArrayList.get(i).getModelName().equals(modelPlanArrayList.get(j).getModelName())){
                        return Result.failure(ResultCode.ERROR_MODELNAME,"第" + (i + 2) +"跟"+(j+2)+ "行数据重复");
                    }
                }
            }
            List<ModelPlan> modelPlans = modelPlanMapper.selectAll();
            for (int i = 0; i < modelPlanArrayList.size(); i++) {
                for (int j = i; j < modelPlans.size(); j++) {
                    if(modelPlanArrayList.get(i).getModelName().equals(modelPlans.get(j).getModelName())){
                        return Result.failure(ResultCode.ERROR_MODELNAME,"设备名为:"+modelPlanArrayList.get(i).getModelName()+"跟excel中第"+(j+2)+"行名称重复");
                    }
                }
            }*/


            modelPlanMapper.addModelPlan(modelPlanArrayList);
            //我们不仅要想model_plan中插入数据，也需要对file_detail表中插入对应的数据
            FileDetail fileDetail = new FileDetail();
            fileDetail.setUploadTime(new Date());
            fileDetail.setFileName(fileName);
            fileDetail.setCount(lastRowNum);
            fileDetail.setUserId(Integer.parseInt(id));
            //我们查询出file_detail数据表中最大的id值，在上面加上1就是我们当前文件在file_detail表中的id值
            int maxId = fileDetailMapper.findMaxId() + 1;
            fileDetail.setId(maxId);
            fileDetailMapper.addFileDetail(fileDetail);
            return Result.success(ResultCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return Result.failure(ResultCode.ERROR_UPLOAD);
        }
    }
}
