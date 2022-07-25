import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

class nodeHeap{
    int index ;//index number
    int length;// length of the queue
    int currentIndex ;//index after swapping
}
class customer {// customer
    int id,state,numb,inTime,outTime;//id of a customer // which state // number of orders of burger // in time of  the customer //out time of the customer
    int counterOutTime ;
    int remainingBurgers ; // number of burger cooked
    nodeHeap heapNode ;
    public customer(int id, int numb ,int inTime)
    {
        this.inTime=inTime;
        this.id=id ;
        this.numb=numb;
        this.remainingBurgers=numb ;
    }
}
class counter_node { // node for billing counters
    int x=0; // number of customer in that row
    Queue<customer> counter =new LinkedList();

}
class minHeap {
    public nodeHeap[] array;//array will contain length of the queues of the different counter
    int size ;
    void sizeMinHeap(int size1){
        array = new nodeHeap[size1+1];//i=1 to size // front is 1st element;
        size=size1+1 ;
        for (int i=1; i<size1+1;i++)
        {
            nodeHeap node = new nodeHeap();
            node.index=i ;
            node.length=0;
            node.currentIndex=i;
            array[i]=node ;
        }
    }
    int parent_pos(int i){
        return i/2 ;
    }
    int right_child_pos(int i )
    {
        return (2*i+1);
    }
    int left_child_pos(int i)
    {
        return (2*i);
    }
    int Find_Min()
    {
        return array[1].index;
    }

    private void swap(int a, int b)
    {


        int x = array[a].currentIndex;
        array[a].currentIndex = array[b].currentIndex;
        array[b].currentIndex =x ;
        nodeHeap temp = array[a];
        array[a]=array[b];
        array[b]=temp ;
    }
    private boolean isLeaf(int pos)
    {
        if (pos >= (size/ 2) && pos <= size) {
            return true;
        }
        return false;
    }


    void Heapify(int i ) // heapifydown
    {
        if(!isLeaf(i))
        {
            if(array[left_child_pos(i)].length <= array[i].length || array[right_child_pos(i)].length <= array[i].length)//i <=size
            {
                if (array[left_child_pos(i)].length< array[right_child_pos(i)].length)
                {
                    if(array[i].length == array[left_child_pos(i)].length)
                    {
                        if(array[i].index > array[left_child_pos(i)].index) {
                            swap(i, left_child_pos(i));
                            Heapify(left_child_pos(i));
                        }

                    }
                    else if(array[i].length > array[left_child_pos(i)].length){
                        swap(i, left_child_pos(i));
                        Heapify(left_child_pos(i));
                    }
                }
                else if(array[right_child_pos(i)].length < array[left_child_pos(i)].length)
                {
                    if(array[i].length == array[right_child_pos(i)].length)
                    {
                        if(array[i].index > array[right_child_pos(i)].index) {
                            swap(i, right_child_pos(i));
                            Heapify(right_child_pos(i));
                        }
                    }
                    else if(array[i].length > array[right_child_pos(i)].length){
                        swap(i, right_child_pos(i));
                        Heapify(right_child_pos(i));
                    }
                }
                else {//array[right_child_pos(i)].length == array[left_child_pos(i)].length
                    if (array[left_child_pos(i)].index< array[right_child_pos(i)].index)
                    {
                        if(array[i].length == array[left_child_pos(i)].length)
                        {
                            if(array[i].index > array[left_child_pos(i)].index) {
                                swap(i, left_child_pos(i));
                                Heapify(left_child_pos(i));
                            }
                        }
                        else if(array[i].length > array[left_child_pos(i)].length)
                        {
                                swap(i, left_child_pos(i));
                                Heapify(left_child_pos(i));
                        }
                    }
                    else if (array[left_child_pos(i)].index > array[right_child_pos(i)].index)
                    {
                        if(array[i].length == array[right_child_pos(i)].length)
                        {
                            if(array[i].index > array[right_child_pos(i)].index) {
                                swap(i, right_child_pos(i));
                                Heapify(right_child_pos(i));
                            }
                        }
                        else if(array[i].length > array[right_child_pos(i)].length)
                        {
                            swap(i,right_child_pos(i));
                            Heapify(right_child_pos(i));
                        }
                    }
                }
                
            }
        }
//        for(int j =1 ;j<size;j++)
//        {
//            System.out.print(" "+array[j].length);
//        }
//        System.out.println();

    }
    void heapifyUp(int i)
    {
        if(i >1 && array[i].length <=array[parent_pos(i)].length)
        {
            if(array[i].length < array[parent_pos(i)].length) {
                swap(i, parent_pos(i));
                heapifyUp(parent_pos(i));
            }
            else
            {
                if(array[i].index < array[parent_pos(i)].index)
                {
                    swap(i,parent_pos(i));
                    heapifyUp(parent_pos(i));
                }
            }
        }
    }
    nodeHeap minNodeHeap()
    {
        return array[1];
    }


    void heapupdater_pos(){
        array[1].length++;
        Heapify(1);
    }

    }
class NodeAVL{
    int id;
    customer node;
    int height;
    nodeHeap nodeHeap ;
    NodeAVL left, right, parent;

}
class AVLTree {
    NodeAVL rootAVL;

    public NodeAVL Insert(int iD){
        NodeAVL new_node = new NodeAVL();
        new_node.id = iD;
        NodeAVL temp= rootAVL;
        if(rootAVL==null)
        {
            rootAVL= new_node ;
            return rootAVL;
        }
        else
        {
            NodeAVL temp_parent=temp;
//			System.out.println("working else of insert");
            while (temp!=null)
            {

                temp_parent=temp;
//				System.out.println("temp_parent  insert:"+temp.id);
                if(temp.id <iD)
                {
                    temp=temp.right;
                }
                else
                {
                    temp=temp.left;
                }
            }


            // now inserting the new_node and setting pointers
            //if the temp_parent has one child then after inserting the height won't change hence the tree will be balanced

            if(temp_parent.left==null && temp_parent.right!=null )
            {
                temp_parent.left = new_node ;
                new_node.parent=temp ;
                new_node.height=1 ;
                return new_node ;
            }

            if(temp_parent.right==null && temp_parent.left!=null)
            {
                temp_parent.right=new_node;
                new_node.parent =temp_parent ;
                new_node.height=1;
                return  new_node ;
            }

            //if temp_parent has no child
            if(temp_parent.id <new_node.id)
            {
                temp_parent.right = new_node ;
                new_node.parent = temp_parent ;
                new_node.height=1;
            }
            else
            {
                temp_parent.left = new_node ;
                new_node.parent=temp_parent ;
                new_node.height=1 ;
            }
            //now balancing the tree and updating height

            if(new_node.parent.parent==null)
            {
                new_node.parent.height++ ;
                return new_node;
            }
//			System.out.println("insert successful");
            NodeAVL temp2 = new_node ;
            while (temp2.parent !=null)
            {
                temp2.height =max(getHeight(temp2.right),getHeight(temp2.left))+1;
                if(balancefactor(temp2)>1)
                    if (getHeight(temp2.left.left)>=getHeight(temp2.left.right))
                    {
                        temp2= RightRotate(temp2);
                    }
                    else
                    {
                        temp2.left =LeftRotate(temp2.left);
                        temp2=RightRotate(temp2);
                    }
                if(balancefactor(temp2)<-1)
                {
                    if(getHeight(temp2.right.left)<= getHeight(temp2.right.right))
                    {
                        temp2=LeftRotate(temp2);
                    }
                    else
                    {
                        temp2.right= RightRotate(temp2.right);
                        temp2=LeftRotate(temp2);
                    }
                }
                temp2=temp2.parent ;
            }

        }

        return new_node ;
    }

    int max(int a, int b) {
        if(a==b)
            return a ;
        return (a>b)? a : b;
    }
    int getHeight(NodeAVL node)
    {
        if(node==null)
            return 0;
        return node.height ;
    }
    int balancefactor(NodeAVL node)
    {
        if(node == null)
            return 0 ;
        return getHeight(node.left) - getHeight(node.right) ;
    }
    NodeAVL RightRotate(NodeAVL y)
    {
        NodeAVL x =y.left ;
        NodeAVL z= x.right ;
        //************************
        // setting pointers
        if(y.parent.right == y)
            y.parent.right = x ;
        else
            y.parent.left = x ;
        x.parent = y.parent ;

        x.right = y;
        if(y!=null)
            y.parent = x ;
        y.left = z;
        if(z!=null)
            z.parent = y ;
        //*****************************updating height *******
        y.height = max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = max(getHeight(x.left), getHeight(x.right)) + 1;
        return x ;
    }
    NodeAVL LeftRotate(NodeAVL y)
    {
        NodeAVL x =y.right ;
        NodeAVL z= x.left ;
        //************************
        // setting pointers
        if(y.parent.right == y)
            y.parent.right = x ;
        else
            y.parent.left = x ;
        x.parent = y.parent ;

        x.left =y ;
        if(y!=null)
            y.parent = x ;
        y.right =z ;
        if(z!=null)
            z.parent = y ;
        //*****************************updating height *******

        y.height = max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = max(getHeight(x.left), getHeight(x.right)) + 1;
        return x ;
    }

    void Delete(NodeAVL temp)//have to write conditions for temp and temp.parent while implementing fireemployee
    {
        if(temp.left==null && temp.right==null)// if node is leaf
        {
            if(temp.parent.right == temp)
            {
                temp = temp.parent ;
                temp.right =null ;
            }
            else
            {
                temp = temp.parent ;
                temp.left = null ;
            }
        }
        else if(temp.left!=null && temp.right!=null)// if node has two child
        {
            temp.id = getNext(temp).id;
            temp.node = getNext(temp).node;
            temp = getNext(temp);
            if (temp.right == null){
                if(temp.parent.right==temp)
                {
                    temp=temp.parent ;
                    temp.right=null ;
                }
                else
                {
                    temp =temp.parent;
                    temp.left=null;
                }
            }
            else
            {
                temp.id = temp.right.id ;
                temp.node= temp.right.node ;

                temp.right=null ;
            }
        }
        else if(temp.left!=null && temp.right==null)// if node has one left child and no right child
        {
            temp.id = temp.left.id ;
            temp.node = temp.left.node ;

            temp.left=null ;
        }
        else {//if node has one right child and no left child
            temp.id = temp.right.id ;
            temp.node = temp.right.node ;

            temp.right=null ;
        }
        // node has been deleted, now balancing of tree will take place
        while (temp.parent!=null)
        {
            temp.height= max(getHeight(temp.left),getHeight(temp.right))+1 ;
            if(balancefactor(temp)>1)
            {
                if(getHeight(temp.left.left)>=getHeight(temp.left.right))
                {
                    temp = RightRotate(temp) ;
                }
                else
                {
                    temp.left = LeftRotate(temp.left);
                    temp=RightRotate(temp);
                }
            }
            if(balancefactor(temp)<-1)
            {
                if(getHeight(temp.right.left)<=getHeight(temp.right.right))
                {
                    temp = LeftRotate(temp) ;
                }
                else
                {
                    temp.right= RightRotate(temp.right);
                    temp=LeftRotate(temp);
                }
            }
            temp = temp.parent ;
        }



    }
    NodeAVL getNext(NodeAVL temp2) // gives successor
    {

        if(temp2.right!=null)
        {
            temp2= temp2.right ;
            while(temp2.left != null)
            {
                temp2 = temp2.left ;
            }
            return temp2 ;
        }
        NodeAVL parent_node = temp2.parent ;
        while(parent_node!=null && temp2 == parent_node.right)
        {

            temp2 = parent_node ;
            parent_node=parent_node.parent;
        }
        return  parent_node ;
    }

    NodeAVL Search(int id)
    {
        NodeAVL temp = rootAVL ;
        while (temp!=null)
        {
            if(temp.id > id)
            {
                temp=temp.left;
            }
            else if(temp.id <id)
            {
                temp=temp.right ;
            }
            else {

                break;
            }
        }
//		System.out.println("working Search ");
        return temp ;
    }
}
class griddleNode{
    int id,griddleInTime,numb;
    int burgerNumber;
    customer customer ;
    griddleNode(int id, int numb, int griddleInTime)
    {
        this.id = id ;
        this.numb= numb;
        this.griddleInTime = griddleInTime;
    }
}
class burger{ // node for griddle vector
    int id ;
    customer customer ;
    burger(int id )
    {
        this.id = id ;
    }
}
public class MMBurgers implements MMBurgersInterface {
    public int k , m , t1 ; //global time
    public minHeap heapChef;
    {
        heapChef = new minHeap();
    }
    // for waiting time------
    public int totalOutTime=0;
    public int totalInTime=0;
    public int totalCustomer=0;
    //-----------------------
    public counter_node[] billing_counters ;// array for billing counters
    public AVLTree idTree = new AVLTree(); // AVL Tree for searching on the basis of id
    public Queue<burger> chef = new LinkedList(); //Queue data structure for queue of customers for burgers .
    public Vector<griddleNode> griddle = new Vector(); // vector for no. of burgers at griddle.
    public Vector<customer>  huehuehue = new Vector();// vector for final delivery of the orders
    public boolean isEmpty(){
        for (int i=1; i <k+1;i++)
        {
            if ( !billing_counters[i].counter.isEmpty())
            {
                return false;
            }
        }
        if(!chef.isEmpty())
            return false ;
        if(!griddle.isEmpty())
            return false;
        return true;
    }
    public void setK(int k) throws IllegalNumberException{
        if(k < 0)
            throw new IllegalNumberException(" ");
        this.k = k ;
        heapChef.sizeMinHeap(k);
	    billing_counters = new counter_node[k+1];// updating size of the array
	    for(int i=1 ;i < k+1;i++)
        {
            counter_node counterNode = new counter_node();
            billing_counters[i] = counterNode;
            Queue<customer> counters =new LinkedList();
            billing_counters[i].counter = counters ;
        }
    }
    
    public void setM(int m) throws IllegalNumberException{
        if(m <0)
            throw new IllegalNumberException(" ");
    this.m =m;
    }

    public void advanceTime(int t) throws IllegalNumberException{
        if(t < t1)
            throw new IllegalNumberException(" ");

	    while(t1<t)// global time should be less than t.
        {
            t1++;// time should update by 1
            while (huehuehue.size()>0)
            {
                huehuehue.elementAt(0).state++;
                huehuehue.remove(0);
            }
            for (int i=k ;i >0 ;i--)//for customer to order
            {

                if(billing_counters[i].counter.peek()!=null) {
                    if (billing_counters[i].counter.peek().counterOutTime == t1) {
                        //newOrder*********
                        burger newOrder = new burger(billing_counters[i].counter.peek().id);
                        newOrder.customer = idTree.Search(billing_counters[i].counter.peek().id).node;
                        //*****************

                        billing_counters[i].x--;
                        NodeAVL temp = idTree.Search(billing_counters[i].counter.peek().id);
                        heapChef.array[temp.nodeHeap.currentIndex].length--;
                        heapChef.heapifyUp(temp.nodeHeap.currentIndex);
                        //-----------------
                        newOrder.customer.state=k+1 ;
                        chef.add(newOrder);
                        billing_counters[i].counter.remove();
                    }
                }
            }
            while (griddle.size()>0)
            {
                if(griddle.elementAt(0).griddleInTime+10==t1)
                {

                    if(griddle.elementAt(0).burgerNumber==griddle.elementAt(0).numb)
                    {

                        griddle.elementAt(0).customer.outTime= t1+1 ;
                        totalOutTime+=t1+1;
                        huehuehue.add(griddle.elementAt(0).customer);

                    }


                    griddle.remove(0);
                }
                else
                    break;//if ith element is not cooked then there is no chance that i+1th element will be cooked
            }
            while (griddle.size()<m)
            {

                if(chef.peek()==null)
                    break;
                griddleNode newBurger = new griddleNode(chef.peek().id,chef.peek().customer.numb,t1);
                newBurger.customer=chef.peek().customer;
                newBurger.burgerNumber = chef.peek().customer.numb - chef.peek().customer.remainingBurgers + 1;
                griddle.add(newBurger);

                chef.peek().customer.remainingBurgers--;

                if(chef.peek().customer.remainingBurgers==0)
                    chef.remove();

            }


        }
    }


    public void arriveCustomer(int id, int t, int numb) throws IllegalNumberException{
        if(t < t1 || numb <0)
            throw new IllegalNumberException(" ");
        if(idTree.Search(id)!=null)
            throw new IllegalNumberException(" ");
        advanceTime(t);
        int i = heapChef.Find_Min();//counter number to which the customer will go
        nodeHeap y= heapChef.minNodeHeap() ;
        heapChef.heapupdater_pos();//will update the min length queue
        totalInTime+=t ;
        totalCustomer++;
        customer customer= new customer(id,numb,t);
        customer.state=i;
        if(billing_counters[i].x==0) {
            billing_counters[i].x ++ ;
            customer.counterOutTime = i + t;
        }
        else {
            billing_counters[i].x ++ ;
            customer.counterOutTime = (billing_counters[i].x - 1) * i + billing_counters[i].counter.peek().counterOutTime + t;
        }
        customer.outTime= customer.counterOutTime;
        billing_counters[i].counter.add(customer);
        // till here the customer is added to the billing queue
        //inserting in avl
        NodeAVL temp = idTree.Insert(id);
        temp.node = customer ;
        temp.nodeHeap = y ;


//        System.out.println("new customer arrived at:"+billing_counters[i].counter.peek().inTime);

    }

    public int customerState(int id, int t) throws IllegalNumberException{
        //your implementation
        if(t < t1)
            throw new IllegalNumberException(" ");
	    advanceTime(t);
	    NodeAVL node = idTree.Search(id);
//	    System.out.println("customerState of: "+id+" is "+node.node.state);
        if(node==null || node.node==null)
            return 0;
	    return node.node.state ;

    } 

    public int griddleState(int t) throws IllegalNumberException{
        if(t < t1)
            throw new IllegalNumberException(" ");
        advanceTime(t);
//        System.out.println("griddleState at t="+t+" is "+griddle.size());
        return griddle.size();
    } 

    public int griddleWait(int t) throws IllegalNumberException{
        if (t < t1)
            throw new IllegalNumberException(" ");
        advanceTime(t);
        int count =0;
        for (burger a : chef)
        {
            count+=a.customer.remainingBurgers;
        }
//        System.out.println("griddleWait at t="+t+" is "+count);
        return count ;
    }

    public int customerWaitTime(int id) throws IllegalNumberException{
        NodeAVL temp = idTree.Search(id);
        if(temp==null)
            throw new IllegalNumberException(" ");
        return temp.node.outTime-temp.node.inTime;
    }

	public float avgWaitTime(){
        float a = totalOutTime - totalInTime ;
        float b = totalCustomer ;

        return a/b ;
    }

}
