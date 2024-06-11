from flask import * 
from database import*



admin=Blueprint('admin',__name__)

@admin.route('/admin_home')
def admin_home():

	return render_template('admin_home.html')


@admin.route('/admin_manageplace',methods=['post','get'])	
def admin_manageplace():
	data={}
	
	q="select * from place "
	res=select(q)
	data['place']=res




	if "action" in request.args:
		action=request.args['action']
		pid=request.args['pid']

	else:
		action=None

	if action=='delete':
		q="delete from place where place_id='%s' "%(pid)
		delete(q)
		flash('successfully')
		return redirect(url_for('admin.admin_manageplace'))

	if action=='update':
		q="select * from place where place_id='%s'"%(pid)
		res=select(q)
		data['placeup']=res
		


	if "update" in request.form:
		p=request.form['place']
		f=request.form['details']
		d=request.form['pincode']
		
		q="update place set place_name='%s' ,place_description='%s',pincode='%s' where place_id='%s' "%(p,f,d,pid)
		update(q)
		flash('successfully')
		return redirect(url_for('admin.admin_manageplace'))


	if "add" in request.form:
		
		p=request.form['place']
		f=request.form['details']
		d=request.form['pincode']
		
		q="insert into place values(null,'%s','%s','%s')"%(p,f,d)
		insert(q)

		flash('successfully')
		return redirect(url_for('admin.admin_manageplace'))
	
	return render_template('admin_manageplace.html',data=data)


@admin.route('/admin_managedepartment',methods=['post','get'])
def admin_managedepartment():
	data={}
	
	q="SELECT * FROM `departments` INNER JOIN `place` USING (`place_id`)"
	res=select(q)
	data['departmentview']=res



	q="select * from place"
	res=select(q)
	data['dropplace']=res


	if "action" in request.args:
		action=request.args['action']
		vid=request.args['vid']

	else:
		action=None
		

	if action=='delete':
		q="delete from departments where dept_id='%s'"%(vid)
		delete(q)
		flash('successfully')
		return redirect(url_for('admin.admin_managedepartment'))

	if action=='update':
		q="select * from departments inner join place using (place_id) where dept_id='%s'"%(vid)
		res=select(q)

		data['departmentup']=res


	if "update" in request.form:
		d=request.form['department']
		p=request.form['phone']
		e=request.form['email']
		de=request.form['description']
		pl=request.form['place']
		q="update departments set dept_name='%s',phone='%s',email='%s',description='%s',place_id='%s' where dept_id='%s'"%(d,p,e,de,pl,vid)
		update(q)
		flash('successfully')
		return redirect(url_for('admin.admin_managedepartment'))


	if "add" in request.form:
		d=request.form['department']
		p=request.form['phone']
		e=request.form['email']
		de=request.form['description']
		pl=request.form['place']
		u=request.form['uname']
		pa=request.form['pwd']
		q="insert into login values(null,'%s','%s','department')" %(u,pa)
		id=insert(q)
		q="insert into departments values(null,'%s','%s','%s','%s','%s','%s')"%(id,pl,d,p,e,de)
		insert(q)
		flash('successfully')
		return redirect(url_for('admin.admin_managedepartment'))
	return render_template('admin_managedepartment.html',data=data)

@admin.route('/adminmessagetopublic')
def adminmessagetopublic():
	data={}
	
	
	q="SELECT * FROM `users` inner join place using (place_id)"
	res=select(q)
	data['pay']=res


	return render_template('adminmessagetopublic.html',data=data)



@admin.route('/admin_viewcomplaints')
def admin_viewcomplaints():
	data={}
	
	
	q="SELECT * FROM `complaints` INNER JOIN users USING (user_id) INNER JOIN `departments` USING (dept_id)"
	res=select(q)
	data['pay']=res


	return render_template('admin_viewcomplaints.html',data=data)

@admin.route('/admin_managerule',methods=['post','get'])	
def admin_managerule():
	data={}
	
	q="select * from rule_regulations"
	res=select(q)
	data['rule']=res




	if "action" in request.args:
		action=request.args['action']
		rid=request.args['rid']

	else:
		action=None

	if action=='delete':
		q="delete from rule_regulations where rule_id='%s' "%(rid)
		delete(q)
		flash('successfully')
		return redirect(url_for('admin.admin_managerule'))

	if action=='update':
		q="select * from rule_regulations where rule_id='%s'"%(rid)
		res=select(q)
		data['ruleup']=res
		


	if "update" in request.form:
		t=request.form['title']
		d=request.form['description']
	
		
		q="update rule_regulations set title='%s',description='%s' where rule_id='%s' "%(t,d,rid)
		update(q)
		flash('successfully')
		return redirect(url_for('admin.admin_managerule'))


	if "add" in request.form:
		
		t=request.form['title']
		d=request.form['description']
		
		
		q="insert into rule_regulations values(null,'%s','%s')"%(t,d)
		insert(q)

		flash('successfully')
		return redirect(url_for('admin.admin_managerule'))
	
	return render_template('admin_managerule.html',data=data)

@admin.route('/adminviewreview')
def adminviewreview():
	data={}
	
	
	q="SELECT * FROM  rating  INNER JOIN `departments` USING (dept_id)"
	res=select(q)
	data['rivew']=res


	return render_template('adminviewreview.html',data=data)

@admin.route('/adminmessagetodepartment')
def adminmessagetodepartment():
	data={}
	q="SELECT * FROM `departments` INNER JOIN `place` USING (`place_id`)"
	res=select(q)
	data['departmentview']=res



	return render_template('adminmessagetodepartment.html',data=data)

@admin.route('/adminmsgdept',methods=['post','get'])
def adminmsgdept():
	data={}
	did=request.args['did']
	q="SELECT * FROM message INNER JOIN departments ON `message`.`receiver_id`=`departments`.`dept_id`WHERE `receiver_id`='%s' and type='department'"%(did)
	res=select(q)
	data['mess']=res

	if "send" in request.form:
		
		m=request.form['msg']
		did=request.args['did']
		
		
		q="insert into message values(null,'%s','department','%s',curdate())"%(did,m)
		insert(q)

		flash('successfully')
		return redirect(url_for('admin.adminmsgdept',did=did))
	



	return render_template('adminmsgdept.html',data=data)

@admin.route('/adminmsgpublic',methods=['post','get'])
def adminmsgpublic():
	data={}
	uid=request.args['uid']
	q="SELECT * FROM message INNER JOIN users ON `message`.`receiver_id`=`users`.`user_id`WHERE `receiver_id`='%s' and type='user'"%(uid)
	res=select(q)
	data['mess']=res

	if "send" in request.form:
		
		m=request.form['msg']
		uid=request.args['uid']
		
		
		q="insert into message values(null,'%s','user','%s',curdate())"%(uid,m)
		insert(q)

		flash('successfully')
		return redirect(url_for('admin.adminmsgpublic',uid=uid))
	



	return render_template('adminmsgpublic.html',data=data)


