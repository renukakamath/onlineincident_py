from flask import Flask 
from public import public
from admin import admin
from department import department
from staff import staff
from api import api


app=Flask(__name__)

app.secret_key='key'

app.register_blueprint(public)
app.register_blueprint(admin,url_prefix='/admin')
app.register_blueprint(department,url_prefix='/department')
app.register_blueprint(staff,url_prefix='/staff')

app.register_blueprint(api,url_prefix='/api')

app.run(debug=True,port=5004,host='0.0.0.0')
