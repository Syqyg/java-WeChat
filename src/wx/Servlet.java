package wx;

import org.omg.PortableServer.REQUEST_PROCESSING_POLICY_ID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Servlet")
public class Servlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.printf("get");

        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        //校验签名
        if (WxService.check(timestamp,nonce,signature)){
            PrintWriter out = response.getWriter();
            //原样返回echostr
            System.out.printf("接入成功");
            out.print(echostr);
            out.flush();
            out.close();
        }else{
            System.out.printf("接入失败");
        }
    }
}
