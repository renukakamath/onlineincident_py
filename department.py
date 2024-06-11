from flask import *
from database import*

department=Blueprint('department',__name__)


@department.route('/department_home')
def department_home():

	return render_template('department_home.html')
@department.route('/deptmanagestaff',methods=['post','get'])
def deptmanagestaff():
	data={}
	did=session['department_id']
	
	q="SELECT * FROM `staff`  inner join departments using (dept_id) where dept_id='%s'"%(did)
	res=select(q)
	data['staffview']=res


	if "action" in request.args:
		action=request.args['action']
		sid=request.args['sid']

	else:
		action=None
		

	if action=='delete':
		q="delete from staff where staff_id='%s'"%(sid)
		delete(q)
		flash('successfully')
		return redirect(url_for('department.deptmanagestaff'))

	if action=='update':
		q="select * from staff  where staff_id='%s'"%(sid)
		res=select(q)

		data['updatestaff']=res


	if "update" in request.form:
		d=request.form['fname']
		p=request.form['lname']
		e=request.form['place']
		de=request.form['phone']
		pl=request.form['email']
		q="update staff set firstname='%s',lastname='%s',place='%s',phone='%s',email='%s' where staff_id='%s'"%(d,p,e,de,pl,sid)
		update(q)
		flash('successfully')
		return redirect(url_for('department.deptmanagestaff'))


	if "register" in request.form:
		d=request.form['fname']
		p=request.form['lname']
		e=request.form['place']
		de=request.form['phone']
		pl=request.form['email']
		u=request.form['uname']
		pa=request.form['pwd']
		did=session['department_id']
		q="insert into login values(null,'%s','%s','staff')" %(u,pa)
		id=insert(q)
		q="insert into staff values(null,'%s','%s','%s','%s','%s','%s','%s')"%(id,did,d,p,e,de,pl)
		insert(q)
		flash('successfully')
		return redirect(url_for('department.deptmanagestaff'))
	return render_template('deptmanagestaff.html',data=data)
@department.route('/departmentviewcomp')
def departmentviewcomp():
	data={}
	did=session['department_id']
	q="SELECT * FROM complaints INNER JOIN users USING(user_id)INNER JOIN departments USING (dept_id)  where dept_id='%s'"%(did)
	res=select(q)
	data['comp']=res



	return render_template('departmentviewcomp.html',data=data)


@department.route('/deptprovidereply',methods=['post','get'])
def deptprovidereply():

	if "send" in request.form:
		cid=request.args['cid']
		reply=request.form['reply']
		q="update complaints set reply='%s' where complaint_id='%s'"%(reply,cid)
		update(q)
		return redirect(url_for('department.departmentviewcomp'))
	return render_template('deptprovidereply.html')


@department.route('/deptmanageactivity',methods=['post','get'])	
def deptmanageactivity():
	data={}
	did=session['department_id']
	
	q="select * from department_actvities inner join departments using (dept_id) where dept_id='%s' "%(did)
	res=select(q)
	data['place']=res




	if "action" in request.args:
		action=request.args['action']
		pid=request.args['aid']

	else:
		action=None

	if action=='delete':
		q="delete from department_actvities where activity_id='%s'"%(pid)
		delete(q)
		flash('successfully')
		return redirect(url_for('department.deptmanageactivity'))

	if action=='update':
		q="select * from department_actvities  inner join departments using (dept_id) where activity_id='%s'"%(pid)
		res=select(q)
		data['placeup']=res
		


	if "update" in request.form:
		p=request.form['title']
		f=request.form['des']
		dat=request.form['dat']
		
		
		q="update department_actvities set title='%s' ,description='%s',activity_date='%s' where activity_id='%s' "%(p,f,dat,pid)
		update(q)
		flash('successfully')
		return redirect(url_for('department.deptmanageactivity'))


	if "register" in request.form:
		
		p=request.form['title']
		f=request.form['des']
		did=session['department_id']
		dat=request.form['dat']
	
		
		q="insert into department_actvities values(null,'%s','%s','%s','%s')"%(did,p,f,dat)
		insert(q)

		flash('successfully')
		return redirect(url_for('department.deptmanageactivity'))
	
	return render_template('deptmanageactivity.html',data=data)

@department.route('/deptaddworktostaff',methods=['post','get'])	
def deptaddworktostaff():
	data={}
	did=session['department_id']
	
	q="select * from work inner join staff using (staff_id) where dept_id='%s' "%(did)
	res=select(q)
	data['works']=res


	q="select * from staff"
	res=select(q)
	data['staffdrop']=res



	if "add" in request.form:
		
		p=request.form['staffid']
		f=request.form['title']
		did=request.form['des']
		
	
		
		q="insert into work values(null,'%s','%s','%s',curdate())"%(p,f,did)
		insert(q)

		flash('successfully')
		return redirect(url_for('department.deptaddworktostaff'))
	
	return render_template('deptaddworktostaff.html',data=data)

@department.route('/deptviewuploadedwork',methods=['post','get'])	
def deptviewuploadedwork():
	data={}


	wid=request.args['wid']
	
	q="select * from uploadwork inner join work using (work_id) where work_id='%s' "%(wid)
	res=select(q)
	data['works']=res




	
	
	return render_template('deptviewuploadedwork.html',data=data)

@department.route('/deptviewreviewrating')
def deptviewreviewrating():
	data={}
	did=session['department_id']
	
	
	q="SELECT * FROM  rating  INNER JOIN `departments` USING (dept_id) WHERE dept_id='%s'"%(did)
	res=select(q)
	data['rivew']=res


	return render_template('deptviewreviewrating.html',data=data)




@department.route('/deptviewmsgfromadmin')
def deptviewmsgfromadmin():
	data={}
	did=session['department_id']
	
	
	q="SELECT * FROM message INNER JOIN departments ON `message`.`receiver_id`=`departments`.`dept_id`WHERE `receiver_id`='%s' and type='department'"%(did)
	res=select(q)
	data['mess']=res


	return render_template('deptviewmsgfromadmin.html',data=data)