package structure;


public class BensList<T> implements ListInterface<T> {
	
	private int listSize=0;
	private BenNode<T> head;
	private BenNode<T> tail;

		public BensList(){
		this.head = null;
		this.tail = null;
	}


	@Override
	public int size() {
		return listSize;
	}

	@Override
	public ListInterface<T> insertFirst(T elem) {
		if(elem==null){
			throw new NullPointerException();
		}
		this.insertAt(0, elem);
//		BenNode<T> newHead = new BenNode<T>(elem);
//		if(this.isEmpty()){
//		head= newHead;	
//		}
//		else{
//		newHead=head.getLink(); 
//		head=newHead;
//		}
	
		return this;
	}

	@Override
	public ListInterface<T> insertLast(T elem) {
		if(elem==null){
			throw new NullPointerException();
		}
		BenNode<T> newTail = new BenNode<T>(elem);
		if(this.isEmpty()){
			head = newTail;
			tail = newTail;
		}else {
			// If the list is not empty, then we add the new node to the end of the list
			tail.setLink(newTail);
			// The new node is now the tail so we update the reference
			tail = newTail;
		}
		listSize++;
		return this;

		}

	@Override
	public ListInterface<T> insertAt(int index, T elem) {
		if(elem==null){
			throw new NullPointerException();
		}
		if(index<0 || index>this.size()){
			throw new IndexOutOfBoundsException();
		}
		listSize++;
		if(index==0){
			BenNode<T> newNode = new BenNode<T>(elem);
			newNode.setLink(head);
			head=newNode;
		}
		
		else insertAtHelper(index-1,elem,head);
		return this;
	}
	
	private void insertAtHelper(int index, T elem, BenNode<T> curr){

		if(index==0){
			BenNode<T> insert = new BenNode<T>(elem);
			BenNode<T> store = curr.getLink();
			curr.setLink(insert);
			insert.setLink(store);
			
		}
		else{insertAtHelper(index-1,elem,curr.getLink());}
		
	}

	@Override
	public T removeFirst() throws IllegalStateException {
		if(this.isEmpty()){
			throw new IllegalStateException();
		}
		T val = head.getInfo();
		head=head.getLink();
		//BenNode<T> node = head.getLink();
		//if(head.getLink()==null){
		//head = head.getLink();} //this points to null sometimes, need to fix
		//node=null;
		listSize--;
		return val;
	}

	@Override
	public T removeLast() {
		if(this.isEmpty()){
			throw new IllegalStateException();
		}
		listSize--;
		return removeLastHelper(head);
	}
	
	public T removeLastHelper(BenNode<T> newTail){
		if(newTail.getLink()==null){
			T val = newTail.getInfo();
			newTail=null;
			return val;
		}
		if(newTail.getLink().getLink()==null){
			T val = newTail.getLink().getInfo();
			newTail.setLink(null);
			return val;
//			if(head.getLink().getLink()==null){
//			head = head.getLink().getLink();}
//			node=null;
		}
		return removeLastHelper(newTail.getLink());
	}

	@Override
	public T removeAt(int i) {
		if(i<0 || i>=this.size()-1){
			throw new IndexOutOfBoundsException();
		}
		listSize--;
		return removeAtHelper(i,head);
	}
	
	public T removeAtHelper(int n,BenNode<T> curr){
		T val=null;
		if(n==1){
		if(curr.getLink().getLink()==null){
			val = curr.getLink().getInfo();
			curr.getLink().setLink(null);
			return val;
		}
		else{ BenNode<T> curr2 = curr.getLink();
		curr.setLink(curr.getLink().getLink());
		val = curr2.getInfo();
		curr2.setLink(null);
		return val;
		}//return val;
		}
		return removeAtHelper(n-1,curr.getLink());
		
	}

	@Override
	public T getFirst() {
		if(this.isEmpty()){
			throw new IllegalStateException();
		}
		T val = head.getInfo();
		return val;
	}

	@Override
	public T getLast() {
		if(this.isEmpty()){
			throw new IllegalStateException();
		}
		return this.get(this.size()-1);
		//return tail.getInfo();
	}

	@Override
	public T get(int i) {
		if(i<0 || i>this.size()){
			throw new IndexOutOfBoundsException();
		}
		return getHelper(i,head);
	}
	
	public T getHelper(int n,BenNode<T> curr){
		T val = null;
		if(n==0){
			val=curr.getInfo();
			return val;
		}
		else{
		
			return getHelper(n-1,curr.getLink());}
	}

	@Override
	public boolean remove(T elem) {
		//listSize--;
		return removeHelper(elem,head);
	}


	public boolean removeHelper(T elem,BenNode<T> curr){
		if(curr.getLink()==null){
			return false;
		}
		else if(curr.getInfo()==elem){
			head = head.getLink();
			curr=null;
			listSize--;
			return true;
		}
		else if(curr.getLink().getInfo()==elem){
			if(curr.getLink().getLink()==null){
				curr.setLink(null);
			}
			else{
				curr.setLink(curr.getLink().getLink());
				//BenNode<T> curr2 = new BenNode<T>(null);
				//curr2 = curr.getLink();
				//curr = curr.getLink().getLink();
				//curr2 = null;
			}
			listSize--;
			return true;
		}
		else
		return removeHelper(elem,curr.getLink());
	}
	@Override
	public int contains(T elem) {
		return containsHelper(0, elem, head);
	}
	
	private int containsHelper(int index, T n, BenNode<T> curr){
		// If curr == null, we've reached the end of the list
		if(curr == null){
			return -1;}
			
		// We know curr is not null here
		// If it contains n, then we can stop and return true
		if(curr.getInfo().equals(n))
			return index;//need to find way to return the index of the curr element
		//Otherwise, we continue down the list
		return (containsHelper(index+1,n, curr.getLink()));
	}

	@Override
	public boolean isEmpty() {
		return (this.size()==0);
	}

}
