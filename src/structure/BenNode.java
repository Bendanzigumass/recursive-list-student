package structure;

	public class BenNode<T> {
		  
		private T element;
		private BenNode<T> link;
		  
		  public BenNode(T element)
		  {
		    this.element = element;
		    link = null;
		  }
		  public BenNode(T element,BenNode<T> link){
			  this.element = element;
			  this.link = link;
		  }
		 
		  
		  public void setInfo(T element)
		  {
		    this.element = element;
		  }

		  public T getInfo()
		  {
		    return element;
		  }
		 
		  public void setLink(BenNode<T> link)	 
		  {
		    this.link = link;
		  }

		  public BenNode<T> getLink()
		  {
		    return link;
		  }

	}

