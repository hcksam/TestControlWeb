package hck.testcontrolweb;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    WebView webView;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

//        String url = "http://www.baidu.com";
        String url = "http://ddns.toraou.com:8888/TestHtml/testForm.html";
        final String javaScript = test();
        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
//        webView.loadDataWithBaseURL("file:///android_asset/", url, "text/html", "UTF-8", null);
        webView.setWebViewClient(new WebViewClient() {
//            @Override
//            public void onLoadResource(WebView view, String url){
//                 view.loadUrl("<script src='//code.jquery.com/jquery-1.12.0.min.js'></script>");
//            }
            @Override
            public void onPageFinished(WebView view, String url) {
//                view.loadUrl("<script src='//code.jquery.com/jquery-1.12.0.min.js'></script>");
                view.loadUrl("javascript:(function() { " +
                        javaScript +
                        "})()");
            }
        });
        webView.loadUrl(url);
    }

    private String test(){
        String javaScript = "";
        javaScript += "$('body').append('test');";
        return javaScript;
    }

    private String searchScript(){
        String javaScript = "document.getElementById('index-kw').value = 'test';";
        javaScript += "document.getElementById('index-form').submit();";
        return javaScript;
    }

    private String jquerySearchScript(){
        String javaScript = "$('#index-kw').val( 'test');";
        javaScript += "$('#index-form').submit();";
        return javaScript;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
