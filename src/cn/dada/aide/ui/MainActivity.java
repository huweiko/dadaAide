package cn.dada.aide.ui;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import cn.dada.aide.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener
{
	HttpResponse httpResponse;
	TextView tvQueryResult;
	String url;
	@Override
	public void onClick(View view)
	{
		url = "http://api.imdada.cn/v4_0/task/randomAvailableNearList/?delivery_range=0&lat=22.590177&lng=114.268191&userid=72818";
		tvQueryResult = (TextView) findViewById(R.id.tvQueryResult);
		EditText etBookName = (EditText) findViewById(R.id.etBookName);
		
		
			switch (view.getId())
			{
				case R.id.btnGetQuery:
					new Thread(){
						public void run(){
							try
							{
	//							url += "?bookname=" + etBookName.getText().toString();
								HttpGet httpGet = new HttpGet(url);
								httpGet.addHeader("Accuracy", "65.000000");
								httpGet.addHeader("UUID", "5182D6BF-DFF2-489C-B2EE-972228913072");
								httpGet.addHeader("App-Name", "i-dada");
								httpGet.addHeader("Unique-Id", "5182D6BF-DFF2-489C-B2EE-972228913072");
								httpGet.addHeader("Location-Provider", "0");
								httpGet.addHeader("OS-Version", "4.3.1");
								httpGet.addHeader("Lng", "114.268191");
								httpGet.addHeader("City-Code", "0755");
								httpGet.addHeader("City-Id", "11");
								httpGet.addHeader("Verification-Hash", "63d4edc1877015298b5f61cae9ca1dbf");
								httpGet.addHeader("App-Version", "3.2.3");
								httpGet.addHeader("Model", "sansung N718");
								httpGet.addHeader("User-Token", "bf2ca13ff8de815af38ba823ac462ea1");
								httpGet.addHeader("Enable", "1");
								httpGet.addHeader("Channel-ID", "A01");
								httpGet.addHeader("Lat", "22.590177");
								httpGet.addHeader("User-Id", "72818");
								httpGet.addHeader("Platform", "Android");
								httpGet.addHeader("Network", "wifi");
								httpGet.addHeader("Sdcard-Id", "5182D6BF-DFF2-489C-B2EE-972228913072");
								httpGet.addHeader("Location-Time", "1443165130756");
								httpResponse = new DefaultHttpClient().execute(httpGet);
								if (httpResponse.getStatusLine().getStatusCode() == 200)
								{
									
									String result = EntityUtils.toString(httpResponse
											.getEntity());
									System.out.println(result.replaceAll("\r", ""));
								}
							}
							catch (Exception e)
							{
								tvQueryResult.setText(e.getMessage());
							}
						}
					}.start();

					break;

				case R.id.btnPostQuery:
					try
					{
						HttpPost httpPost = new HttpPost(url);
						List<NameValuePair> params = new ArrayList<NameValuePair>();
						params.add(new BasicNameValuePair("bookname", etBookName
								.getText().toString()));
						httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
						
						httpResponse = new DefaultHttpClient().execute(httpPost);
						if (httpResponse.getStatusLine().getStatusCode() == 200)
						{
							String result = EntityUtils.toString(httpResponse
									.getEntity());
							tvQueryResult.setText(result.replaceAll("\r", ""));
						}
					}
					catch (Exception e)
					{
						tvQueryResult.setText(e.getMessage());
					}
					break;
			}
		

	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		Button btnGetQuery = (Button) findViewById(R.id.btnGetQuery);
		Button btnPostQuery = (Button) findViewById(R.id.btnPostQuery);
		btnGetQuery.setOnClickListener(this);
		btnPostQuery.setOnClickListener(this);
		
	}
}