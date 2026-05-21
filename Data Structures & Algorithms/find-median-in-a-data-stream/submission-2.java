class MedianFinder {
    PriorityQueue<Integer> leftMaxHeap;
    PriorityQueue<Integer> rightMinHeap;

    public MedianFinder() {
        leftMaxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        rightMinHeap = new PriorityQueue<>();
    }
    
    public void addNum(int num) { 
        if(!rightMinHeap.isEmpty() && num >= rightMinHeap.peek()){
            rightMinHeap.add(num);
        }else{
            leftMaxHeap.add(num);
        }
        //if the two heap sizes differ by > 1 then must rebalance
        rebalance(leftMaxHeap, rightMinHeap);
    }
    
    public double findMedian() {
        if(leftMaxHeap.size() == rightMinHeap.size()){
            return (leftMaxHeap.peek() + rightMinHeap.peek()) / 2.0;
        }else if(leftMaxHeap.size() > rightMinHeap.size()){
            return leftMaxHeap.peek();
        }else{
            return rightMinHeap.peek();
        }
    }

    private void rebalance(PriorityQueue<Integer> small, PriorityQueue<Integer> large){
        while(Math.abs(small.size() - large.size()) > 1){
            if(small.size() > large.size()){
                large.add(small.poll());
            }else{
                small.add(large.poll());
            }
        }
    }
}
