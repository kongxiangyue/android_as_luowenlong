from flask import Flask
from flask import request

app = Flask(__name__)


@app.route('/')
def hello_world():
    return 'Hello World!'

@app.route('/MyServer/Servlet/LoginServlet', methods=['GET', 'POST'])
def login():

    username = request.args.get('username')
    password = request.args.get('password')
    if request.method == 'POST':
        username = request.form['username']
        password = request.form['password']

    msg = 'Login faileded'
    while True:
        if None == username \
                or None == password:
            break

        if 'admin' != username \
                or '123456' != password:
            break
        msg = 'Login succeeded!'
        break

    return msg




if __name__ == '__main__':
    app.run(host='0.0.0.0')
