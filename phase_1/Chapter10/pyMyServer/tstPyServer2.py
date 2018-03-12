import urllib
import urllib2
url = "http://172.16.33.191:5000/MyServer/Servlet/LoginServlet" \
      "?username=admin&password=123456"
req = urllib2.Request(url)
print req
res_data = urllib2.urlopen(req)
res = res_data.read()
print res