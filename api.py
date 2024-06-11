from flask import * 
from database import* 
import uuid

api=Blueprint('api',__name__)

@api.route('/login')
def login():
	data={}
	u=request.args['username']
	p=request.args['password']
	q1="select * from login where username='%s' and `password`='%s'"%(u,p)
	print(q1)
	res=select(q1)
	if res:
		data['data']=res
		data['status']='success'
	else:
		data['status']='failed'
	return str(data)

@api.route('/Registration')
def Registration():
	data={}


	pid=request.args['pid']
	f=request.args['fname']
	l=request.args['lname']
	a=request.args['address']
	
	
	
	ph=request.args['phone']
	e=request.args['email']
	u=request.args['username']
	p=request.args['password']
	q="select * from login where username='%s' and password='%s'"%(u,p)
	res=select(q)
	if res:
		data['status']='already'
	else:
		q="insert into login values(NULL,'%s','%s','User')"%(u,p)
		lid=insert(q)
		r="insert into users values(NULL,'%s','%s','%s','%s','%s','%s','%s')"%(lid,pid,f,l,a,ph,e)
		insert(r)
		data['status']="success"
		data['method']="Registration"
	return str(data)


@api.route('/viewproductspinner')
def viewproductspinner():
	data={}
	q="select * from place"
	res=select(q)
	if res:
		data['data']=res
		data['status']="success"
		data['method']="viewproductspinner"
	return str(data)


@api.route('/Viewactivity')
def Viewactivity():
	data={}
	did=request.args['did']
	q="select * from department_actvities where dept_id='%s'"%(did)
	res=select(q)
	if res:
		data['data']=res
		data['status']="success"
		
	return str(data)

@api.route('/Viewdepartments')
def Viewdepartments():
	data={}
	
	q="select * from departments inner join place using (place_id)"
	res=select(q)
	if res:
		data['data']=res
		data['status']="success"
		
	return str(data)

@api.route('/viewmessageadmin')
def viewmessageadmin():
	data={}
	
	q="select * from message inner join users on users.user_id=message.receiver_id "
	res=select(q)
	if res:
		data['data']=res
		data['status']="success"
		
	return str(data)

@api.route('/Viewpubliccomplaints')
def Viewpubliccomplaints():
	data={}
	login_id=request.args['login_id']
	
	q="select * from complaints inner join users using (user_id) inner join departments using (dept_id) where users.login_id='%s'"%(login_id)
	res=select(q)
	if res:
		data['data']=res
		data['status']="success"
		
	return str(data)


@api.route('/viewrules')
def viewrules():
	data={}
	
	q="select * from rule_regulations "
	res=select(q)
	if res:
		data['data']=res
		data['status']="success"
		
	return str(data)

@api.route('/rate')
def rate():
	data={}
	rate=request.args['rating']
	log_id=request.args['log_id']
	review=request.args['review']
	did=request.args['did']
	q="insert into rating values(null,'%s','%s','%s','%s',now())"%(log_id,did,review,rate)
	insert(q)
	print(q)
	data['status']="success"
	return str(data)


@api.route('/Complaint')
def Complaint():
	data={}
	c=request.args['compliant']
	l=request.args['login_id']
	did=request.args['did']
	tit=request.args['tit']

	q="insert into complaints values(null,(select user_id from users where login_id='%s'),'%s','%s','%s','pending',now())"%(l,did,c,tit)
	insert(q)
	data['status']='success'
	data['method']="Complaint"
	return str(data)


@api.route('/viewspinner')
def viewspinner():
	data={}
	
	q="select * from departments "
	res=select(q)
	if res:
		data['data']=res
		data['status']="success"
		data['method']="viewspinner"

		
	return str(data)

@api.route('/viewcomplaints')
def viewcomplaints():
	data={}
	
	q="select * from complaints inner join users using (user_id) inner join departments using (dept_id) "
	res=select(q)
	if res:
		data['data']=res
		data['status']="success"
		data['method']="viewcomplaints"

		
	return str(data)


@api.route('/uploadimage',methods=['get','post'])
def uploadimage():
	data={}
	
	uid=request.form['login_id']
	cid=request.form['cid']

	img=request.files['image']
	path="static/image/"+str(uuid.uuid4())+img.filename
	img.save(path)



	q="insert into complaint_images values(null,'%s','%s',curdate())"%(cid,path)
	insert(q)
	data['status']='success'
	return str(data)









