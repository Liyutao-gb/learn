package com.lyt.learn.model;

public class Word {

	private int id;
	private String content;
	private String values;
	private String sentence;
	private String belongTo;
	private String createTime;

	public Word() {
	}

	public Word(int id, String content, String values, String sentence, String belongTo, String createTime) {
		super();
		this.id = id;
		this.content = content;
		this.values = values;
		this.sentence = sentence;
		this.belongTo = belongTo;
		this.createTime = createTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getValues() {
		return values;
	}

	public void setValues(String values) {
		this.values = values;
	}

	public String getSentence() {
		return sentence;
	}

	public void setSentence(String sentence) {
		this.sentence = sentence;
	}

	public String getBelongTo() {
		return belongTo;
	}

	public void setBelongTo(String belongTo) {
		this.belongTo = belongTo;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Word [id=");
		builder.append(id);
		builder.append(", content=");
		builder.append(content);
		builder.append(", values=");
		builder.append(values);
		builder.append(", sentence=");
		builder.append(sentence);
		builder.append(", belongTo=");
		builder.append(belongTo);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append("]");
		return builder.toString();
	}

}
