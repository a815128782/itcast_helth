package com.itheima.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

/**
 * ���ŷ��͹�����
 */
public class SMSUtils {
	public static final String VALIDATE_CODE = "SMS_177536814";//���Ͷ�����֤��
	public static final String ORDER_NOTICE = "SMS_177551816";//���ԤԼ�ɹ�֪ͨ
	/**
	 * ���Ͷ���
	 * @param phoneNumbers
	 * @param param
	 * @throws ClientException
	 */
	public static void sendShortMessage(String templateCode,String phoneNumbers,String param) throws ClientException{
		// ���ó�ʱʱ��-�����е���
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");
		// ��ʼ��ascClient��Ҫ�ļ�������
		final String product = "Dysmsapi";// ����API��Ʒ���ƣ����Ų�Ʒ���̶��������޸ģ�
		final String domain = "dysmsapi.aliyuncs.com";// ����API��Ʒ�������ӿڵ�ַ�̶��������޸ģ�
		// �滻�����AK
		final String accessKeyId = "LTAI4FqNoGbjv4F7DQzhRhjH";// ���accessKeyId,�ο����ĵ�����2
		final String accessKeySecret = "xbecACL4FilsrnjXeooI7oECumlBd5";// ���accessKeySecret���ο����ĵ�����2
		// ��ʼ��ascClient,��ʱ��֧�ֶ�region�������޸ģ�
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
		IAcsClient acsClient = new DefaultAcsClient(profile);
		// ��װ�������
		SendSmsRequest request = new SendSmsRequest();
		// ʹ��post�ύ
		request.setMethod(MethodType.POST);
		// ����:�������ֻ��š�֧���Զ��ŷָ�����ʽ�����������ã���������Ϊ1000���ֻ�����,������������ڵ������ü�ʱ�������ӳ�,��֤�����͵Ķ����Ƽ�ʹ�õ������õķ�ʽ
		request.setPhoneNumbers(phoneNumbers);
		// ����:����ǩ��-���ڶ��ſ���̨���ҵ�
		request.setSignName("���ǽ���");
		// ����:����ģ��-���ڶ��ſ���̨���ҵ�
		request.setTemplateCode(templateCode);
		// ��ѡ:ģ���еı����滻JSON��,��ģ������Ϊ"�װ���${name},������֤��Ϊ${code}"ʱ,�˴���ֵΪ
		// ������ʾ:���JSON����Ҫ�����з�,����ձ�׼��JSONЭ��Ի��з���Ҫ��,������������а���\r\n�������JSON����Ҫ��ʾ��\\r\\n,����ᵼ��JSON�ڷ���˽���ʧ��
		request.setTemplateParam("{\"code\":\""+param+"\"}");
		// ��ѡ-���ж�����չ��(��չ���ֶο�����7λ�����£������������û�����Դ��ֶ�)
		// request.setSmsUpExtendCode("90997");
		// ��ѡ:outIdΪ�ṩ��ҵ����չ�ֶ�,�����ڶ��Ż�ִ��Ϣ�н���ֵ���ظ�������
		// request.setOutId("yourOutId");
		// ����ʧ���������ClientException�쳣
		SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
		if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
			// ����ɹ�
			System.out.println("����ɹ�");
		}else{
			System.out.println("����ʧ��");
		}
	}

	public static void main(String[] args) throws ClientException {
		SMSUtils.sendShortMessage("SMS_177536814","18655227081","3344");
	}
}
