# -*- coding: utf-8 -*-
from flask import Flask, render_template
from flask import request
import json

app = Flask(__name__)


@app.route('/')
def hello_world():
    return 'Hello World!'

@app.route('/for_webview', methods=['GET', 'POST'])
def for_webview():

    id   = request.args.get('id')
    name = request.args.get('name')
    if request.method == 'POST':
        id   = request.form['id']
        name = request.form['name']

    if None == id :
        id = u'数据不完整'
    if None == name :
        name = u'数据不完整'

    return render_template('index.html', id=id, name=name)

@app.route('/for_get', methods=['GET', 'POST'])
def for_get():

    id   = request.args.get('id')
    name = request.args.get('name')
    if request.method == 'POST':
        id   = request.form['id']
        name = request.form['name']

    ret = u"提交不完全，只能得一半分"
    while True :
        if None != id \
                and None != name :
            ret = u"这是 %s 的提交，学号 %s" % (name, id)
        break;

    return ret

if __name__ == '__main__':
    app.run(host='0.0.0.0')
