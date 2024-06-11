from flask import *
from database import*
import uuid

staff=Blueprint('staff',__name__)


@staff.route('/staff_home')
def staff_home():

	return render_template('staff_home.html')

@staff.route('/staffviewcomplaint')
def staffviewcomplaint():
	data={}
	sid=session['staff_id']
	q="SELECT * FROM complaints INNER JOIN users USING(user_id)INNER JOIN departments USING (dept_id) INNER JOIN `staff` USING(`dept_id`) where staff_id='%s'"%(sid)
	res=select(q)
	data['comp']=res



	return render_template('staffviewcomplaint.html',data=data)

@staff.route('/staffviewactivity')
def staffviewactivity():
	data={}
	
	q="SELECT * FROM `department_actvities` INNER JOIN `departments` USING (`dept_id`)"
	res=select(q)
	data['act']=res



	return render_template('staffviewactivity.html',data=data)

@staff.route('/staffviewreview')
def staffviewreview():
	data={}
	
	
	q="SELECT * FROM  rating  INNER JOIN `departments` USING (dept_id)"
	res=select(q)
	data['rivew']=res


	return render_template('staffviewreview.html',data=data)

@staff.route('/staffviewwork')
def staffviewwork():
	data={}

	sid=session['staff_id']
	
	
	q="SELECT * FROM work INNER JOIN staff USING(staff_id) where staff_id='%s'"%(sid)
	res=select(q)
	data['works']=res


	return render_template('staffviewwork.html',data=data)

@staff.route('/staffuploadwork',methods=['post','get'])
def staffuploadwork():
	data={}
	wid=request.args['wid']

	q="SELECT * FROM uploadwork inner join work using (work_id) where work_id='%s'"%(wid)
	res=select(q)
	data['workupload']=res



	if "upload" in request.form:
		wid=request.args['wid']
		i=request.files['fpath']
		path="static/image"+str(uuid.uuid4())+i.filename
		i.save(path)
		q="insert into uploadwork values(null,'%s','%s',curdate())"%(wid,path)
		insert(q)
		return redirect(url_for('staff.staffuploadwork',wid=wid))


	return render_template('staffuploadwork.html',data=data)

@staff.route('/staffprovidereply',methods=['post','get'])
def staffprovidereply():
	if "send" in request.form:
		cid=request.args['cid']
		reply=request.form['reply']
		q="update complaints set reply='%s' where complaint_id='%s'"%(reply,cid)
		update(q)
		return redirect(url_for('staff.staffviewcomplaint'))
	return render_template('staffprovidereply.html')