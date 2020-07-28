package com.dhcens.emviewdoctor.service.impl;


import com.dhcens.emviewdoctor.service.RedisWebService;
import com.dhcens.emviewdoctor.util.RedisUtil;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebService;

/**
 * 保存主数据实时数据到数据中心的Webservice的实现类
 * @author zhibao
 */
@WebService(serviceName = "RedisWebService", // 与接口中指定的name一致
        targetNamespace = "http://dhcc.dhcens.com/" // 与接口中的命名空间一致,一般是接口的包名倒
)
@Component
@Service
public class RedisWebServiceImpl implements RedisWebService {

    @Resource
    RedisUtil redisUtil;

    /**
     * redis缓存接受实时变动的字典服务
     * @param tableName 表名
     * @param paremters 入参字符串
     * @return 结果字符串
     */
    @Override
    public String saveDictToRedis(String tableName, String paremters) {

        String retstr = "";
        String xmlPath="";String codeName ="";
        String descName = "";
        try {
            switch (tableName){
                case "CT_Dept":
                    xmlPath = "//Request/Body/CT_DeptList/CT_Dept";
                    codeName = "CTD_Code";
                    descName = "CTD_Desc";
                    break;
                case "CT_EncounterType":
                    xmlPath = "//Request/Body/CT_EncounterTypeList/CT_EncounterType";
                    codeName = "CTET_Code";
                    descName = "CTET_Desc";
                    break;
                case "CT_Nation":
                    xmlPath = "//Request/Body/CT_NationList/CT_Nation";
                    codeName = "CTN_Code";
                    descName = "CTN_Desc";
                    break;
                case "CT_Country":
                    xmlPath = "//Request/Body/CT_CountryList/CT_Country";
                    codeName = "CTC_Code";
                    descName = "CTC_Desc";
                    break;
                case "CT_Marital":
                    xmlPath = "//Request/Body/CT_MaritalList/CT_Marital";
                    codeName = "CTM_Code";
                    descName = "CTM_Desc";
                     break;
                case "CT_ARCItmMast":
                    xmlPath = "//Request/Body/CT_ARCItmMastList/CT_ARCItmMast";
                    codeName = "CTARCIM_Code";
                    descName = "CTARCIM_Desc";
                    break;
                case "CT_Priority":
                    xmlPath = "//Request/Body/CT_PriorityList/CT_Priority";
                    codeName = "CTP_Code";
                    descName = "CTP_Desc";
                    break;
                case "CT_DoseForms":
                    xmlPath = "//Request/Body/CT_DoseFormsList/CT_DoseForms";
                    codeName = "CTDF_Code";
                    descName = "CTDF_Desc";
                    break;
                case "CT_Freq":
                    xmlPath = "//Request/Body/CT_DeptList/CT_Dept";
                    codeName = "CTD_Code";
                    descName = "CTD_Desc";
                    break;
                case "CT_Instr":
                    xmlPath = "//Request/Body/CT_InstrList/CT_Instr";
                    codeName = "CTI_Code";
                    descName = "CTI_Desc";
                    break;
                case "CT_Duration":
                    xmlPath = "//Request/Body/CT_DurationList/CT_Duration";
                    codeName = "CTD_Code";
                    descName = "CTD_Desc";
                    break;
                case "CT_DoseUnit":
                    xmlPath = "//Request/Body/CT_DoseUnitList/CT_DoseUnit";
                    codeName = "CTDU_Code";
                    descName = "CTDU_Desc";
                    break;
                case "CT_CareProv":
                    xmlPath = "//Request/Body/CT_CareProvList/CT_CareProv";
                    codeName = "CTCP_Code";
                    descName = "CTCP_Desc";
                    break;
                case "SS_User":
                    xmlPath = "//Request/Body/SS_UserList/SS_User";
                    codeName = "SS_UserId";
                    descName = "SS_UserDesc";
                    break;
                case "CT_OrderStatus":
                    xmlPath = "//Request/Body/CT_OrderStatusList/CT_OrderStatus";
                    codeName = "CTOS_Code";
                    descName = "CTOS_Desc";
                    break;
                case "CT_Status":
                    xmlPath = "//Request/Body/CT_StatusList/CT_Status";
                    codeName = "CTS_Code";
                    descName = "CTS_Desc";
                    break;
                default:
                   return commonResponse(false);
            }
            retstr = saveDictnfoToRedis(paremters, xmlPath, codeName, descName, tableName);
            return retstr;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * TODO:根据json串和主题代码，判断数据是否已经被处理过，设置超时时间为一个小时
     * @param themeCode 主题代码
     * @param jsonStr 主题生成的json字符串
     * @return  存在返回1，不存在返回0，并且将数据保存到redis，
     */
    @Override
    public boolean compareJsonStr(String themeCode, String jsonStr) {
        try {
            boolean tsc = redisUtil.hHasKey(themeCode, jsonStr);
            if (!tsc){
                boolean savetsc = redisUtil.hset(themeCode, jsonStr, "1",3600);
            }
            return tsc;
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 保存就诊类型字符串
     * @param paremters
     * @return
     * @throws Exception
     */
    public String saveDictnfoToRedis(String paremters,String xmlPath,String codeName,String descName,String tableName) throws Exception{
        boolean flag = true;
        Document document = DocumentHelper.parseText(paremters);
        List<Element> nodes = document.selectNodes(xmlPath);
        for (int i = 0; i <nodes.size() ; i++) {
            Element element=nodes.get(i);
            Element codeNode=(Element) element.selectSingleNode(codeName);
            String codeValue = codeNode.getTextTrim();
            Element descNode=(Element) element.selectSingleNode(descName);
            String descValue = descNode.getTextTrim();

            boolean tsc = redisUtil.hset(tableName.toLowerCase(), codeValue, descValue);
            if (!tsc){
                flag = false;
            }
        }
        return commonResponse(flag);
    }
    /**
     * 根据状态变量输出结果字符串
     * @param falg  状态变量
     * @return 结果字符串
     */

    private String commonResponse(boolean falg){
        if (falg){
            return "<Response><Header><SourceSystem>01</SourceSystem><MessageID></MessageID></Header><Body><ResultCode>0</ResultCode><ResultContent>成功</ResultContent></Body></Response>";
        }
        else{
            return "<Response><Header><SourceSystem>01</SourceSystem><MessageID></MessageID></Header><Body><ResultCode>-1</ResultCode><ResultContent>失败</ResultContent></Body></Response>";
        }


    }

}
