package cn.stylefeng.guns.core.notice;

import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.green.model.v20180509.ImageSyncScanRequest;
import com.aliyuncs.http.FormatType;
import com.aliyuncs.http.HttpResponse;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import cn.stylefeng.guns.config.ConfigEntity;
import cn.stylefeng.guns.core.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AliyunGreen {

	@Resource
	private ConfigEntity configEntity;

	public void checkImage(String url) {
		try {
			IClientProfile profile = DefaultProfile.getProfile("cn-shanghai", configEntity.getAliyunAccessKeyId(),
					configEntity.getAliyunSecret());
			DefaultProfile.addEndpoint("cn-shanghai", "cn-shanghai", "Green", "green.cn-shanghai.aliyuncs.com");
			IAcsClient client = new DefaultAcsClient(profile);
			ImageSyncScanRequest imageSyncScanRequest = new ImageSyncScanRequest();
	        // 指定api返回格式
	        imageSyncScanRequest.setAcceptFormat(FormatType.JSON);
	        // 指定请求方法
	        imageSyncScanRequest.setMethod(MethodType.POST);
	        imageSyncScanRequest.setEncoding("utf-8");
	        //支持http和https
	        imageSyncScanRequest.setProtocol(ProtocolType.HTTP);
	        JSONObject httpBody = new JSONObject();
	        /**
	         * 设置要检测的场景, 计费是按照该处传递的场景进行
	         * 一次请求中可以同时检测多张图片，每张图片可以同时检测多个风险场景，计费按照场景计算
	         * 例如：检测2张图片，场景传递porn、terrorism，计费会按照2张图片鉴黄，2张图片暴恐检测计算
	         * porn: porn表示色情场景检测
	         */
	        httpBody.put("scenes", Arrays.asList("porn", "terrorism", "ad"));

	        /**
	         * 设置待检测图片， 一张图片一个task
	         * 多张图片同时检测时，处理的时间由最后一个处理完的图片决定
	         * 通常情况下批量检测的平均rt比单张检测的要长, 一次批量提交的图片数越多，rt被拉长的概率越高
	         * 这里以单张图片检测作为示例, 如果是批量图片检测，请自行构建多个task
	         */
	        JSONObject task = new JSONObject();
	        task.put("dataId", UUID.randomUUID().toString());

	        //设置图片链接
	        task.put("url", url);
	        task.put("time", new Date());
	        httpBody.put("tasks", Arrays.asList(task));

	        imageSyncScanRequest.setHttpContent(org.apache.commons.codec.binary.StringUtils.getBytesUtf8(httpBody.toJSONString()),
	            "UTF-8", FormatType.JSON);

	        /**
	         * 请设置超时时间, 服务端全链路处理超时时间为10秒，请做相应设置
	         * 如果您设置的ReadTimeout小于服务端处理的时间，程序中会获得一个read timeout异常
	         */
	        imageSyncScanRequest.setConnectTimeout(3000);
	        imageSyncScanRequest.setReadTimeout(10000);
	        HttpResponse httpResponse = null;
            httpResponse = client.doAction(imageSyncScanRequest);

	        //服务端接收到请求，并完成处理返回的结果
	        if (httpResponse != null && httpResponse.isSuccess()) {
	            JSONObject scrResponse = JSON.parseObject(org.apache.commons.codec.binary.StringUtils.newStringUtf8(httpResponse.getHttpContent()));
	            log.info(JSON.toJSONString(scrResponse, true));
	            int requestCode = scrResponse.getIntValue("code");
	            //每一张图片的检测结果
	            JSONArray taskResults = scrResponse.getJSONArray("data");
	            if (200 == requestCode) {
	                for (Object taskResult : taskResults) {
	                    //单张图片的处理结果
	                    int taskCode = ((JSONObject) taskResult).getIntValue("code");
	                    //图片要检测的场景的处理结果, 如果是多个场景，则会有每个场景的结果
	                    JSONArray sceneResults = ((JSONObject) taskResult).getJSONArray("results");
	                    if (200 == taskCode) {
	                        for (Object sceneResult : sceneResults) {
	                            String scene = ((JSONObject) sceneResult).getString("scene");
	                            String suggestion = ((JSONObject) sceneResult).getString("suggestion");
	                            //根据scene和suggetion做相关处理
	                            log.info("scene = [" + scene + "]");
	                            log.info("suggestion = [" + suggestion + "]");
	                            if (!suggestion.equals("pass")) {
	                            	log.info("图片包含敏感内容, url=" + url);
	                            	throw new ServiceException("图片包含敏感内容");
	                            }
	                        }
	                    } else {
	                        //单张图片处理失败, 原因视具体的情况详细分析
	                    	String logMessage = "task process fail. task response:" + JSON.toJSONString(taskResult);
	                        log.error(logMessage);
	                        throw new ServiceException(logMessage);
	                    }
	                }
	            } else {
	                /**
	                 * 表明请求整体处理失败，原因视具体的情况详细分析
	                 */
	            	String logMessage = "the whole image scan request failed. response:" + JSON.toJSONString(scrResponse);
	                log.error(logMessage);
	                throw new ServiceException(logMessage);
	            }
	        }
		} catch (ClientException e) {
			log.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
		
	}
}
