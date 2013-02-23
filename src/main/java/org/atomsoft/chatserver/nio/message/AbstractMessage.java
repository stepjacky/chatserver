package org.atomsoft.chatserver.nio.message;

public class AbstractMessage implements Message {

	protected int mtype;
	protected Object body;

	
	
	public AbstractMessage(int mtype) {
		super();
		this.mtype = mtype;
	}

	public AbstractMessage(int mtype, Object body) {
		super();
		this.mtype = mtype;
		this.body = body;
	}

	@Override
	public int getMtype() {

		return mtype;
	}

	@Override
	public void setMtype(int t) {
		// TODO Auto-generated method stub
		this.mtype = t;
	}

	@Override
	public Object getBody() {
		// TODO Auto-generated method stub
		return this.body;
	}

	@Override
	public void setBody(Object body) {
		// TODO Auto-generated method stub
		this.body = body;
	}

}
