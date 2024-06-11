from flask import *
from database import*


public=Blueprint('public',__name__)

@public.route('/')
def home():
	return render_template('home.html')




@public.route('/login',methods=['post','get'])	
def login():
	if "login" in request.form:
		u=request.form['uname']
		pa=request.form['pwd']
		q="select * from login where username='%s' and password='%s'"%(u,pa)
		res=select(q)
		if res:
			session['login_id']=res[0]['login_id']
			lid=session['login_id']
			if res[0]['usertype']=="admin":
				return redirect(url_for('admin.admin_home'))

			elif res[0]['usertype']=="staff":
				q="select * from staff where login_id='%s'"%(lid)
				res=select(q)
				if res:
					session['staff_id']=res[0]['staff_id']
					sid=session['staff_id']
				return redirect(url_for('staff.staff_home'))

			elif res[0]['usertype']=="department":
				q="select * from departments where login_id='%s'"%(lid)
				res=select(q)
				if res:
					session['department_id']=res[0]['dept_id']
					did=session['department_id']



				return redirect(url_for('department.department_home'))


		else:
			flash('invalid username and password')

	return render_template('login.html')
