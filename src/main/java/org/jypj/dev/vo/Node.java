package org.jypj.dev.vo;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class Node {

	  private static final long serialVersionUID = -2721191232926604726L;

	    private String id;

	    private String parentId;

	    @JSONField(serialize = false)
	    private Node parent;

	    private List<Node> menuSecond;

	    private String name;

	    private String url;
	    
	    private int index;

	    public Node() {
	        super();
	    }
	    public Node(String id, String parentId, String name,int sort) {
	        super();
	        this.id = id;
	        this.parentId = parentId;
	        this.name = name;
	      
	    }
	    public Node getParent() {
	        return parent;
	    }

	    public void setParent(Node parent) {
	        this.parent = parent;
	    }



	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public List<Node> getMenuSecond() {
			return menuSecond;
		}
		public void setMenuSecond(List<Node> menuSecond) {
			this.menuSecond = menuSecond;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result + ((parentId == null) ? 0 : parentId.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			if (parentId == null) {
				if (other.parentId != null)
					return false;
			} else if (!parentId.equals(other.parentId))
				return false;
			return true;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getParentId() {
			return parentId;
		}

		public void setParentId(String parentId) {
			this.parentId = parentId;
		}


		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}
		public int getIndex() {
			return index;
		}
		public void setIndex(int index) {
			this.index = index;
		}
		
		
}
