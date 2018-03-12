import urllib
import urllib2

post_form = {'username':'admin'
    ,'password':'123456'}

requrl = "http://172.16.33.191:5000/MyServer/Servlet/LoginServlet"

post_form_urlencode = urllib.urlencode(post_form)
req = urllib2.Request(url = requrl,data =post_form_urlencode)

print req
res_data = urllib2.urlopen(req)
res = res_data.read()
print res