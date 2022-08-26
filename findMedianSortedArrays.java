class C {
    int nums1,nums2,moving;

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if( nums1.length == 0 ){
            if( nums2.length%2 == 1 ) return nums2[ nums2.length/2 ];
            return (double)( nums2[ nums2.length/2 ] + nums2[ nums2.length/2 - 1 ] )/2;
        }else if( nums2.length == 0 ){
            if( nums1.length%2 == 1 ) return nums1[ nums1.length/2 ];
            return (double)( nums1[ nums1.length/2 ] + nums1[ nums1.length/2 - 1 ] )/2;
        }

        this.nums1=nums1.length; int s1=( nums1.length - 1 )/2;
        this.nums2=nums2.length; int s2=( nums2.length - 1 )/2;
        this.moving=s1+s2;

        int bias;
        while(true){
            System.out.println("s1= "+nums1[s1]+" s2= "+nums2[s2]);
            if( nums1[s1] > nums2[s2] ){
                if( s2+1==nums2.length || nums1[s1]<=nums2[s2+1]){
                    if( ( nums1.length + nums2.length )%2 == 1 ) return nums1[s1];
                    if( s1 != 0 ){
                        if( nums1[s1-1] >= nums2[s2]) return (double)( nums1[s1] + nums1[s1-1] )/2;
                        return (double)( nums1[s1] + nums2[s2] )/2;
                    } 
                    return (double)( nums1[0] + nums2[ nums2.length - 1 ] )/2;
                } 
                bias=moveL(s1,s2);
                s1-=bias;
                s2+=bias;

            }else if( nums1[s1] < nums2[s2] ){
                if( s1+1==nums1.length || nums2[s2]<=nums1[s1+1]){
                    if( ( nums1.length + nums2.length )%2 == 1 ) return nums2[s2];
                    if( s2 != 0 ){
                        if( nums2[s2-1] >= nums1[s1]) return (double)( nums2[s2] + nums1[s2-1] )/2;
                        return (double)( nums1[s1] + nums2[s2] )/2;
                    } 
                    return (double)( nums2[0] + nums1[ nums1.length - 1 ] )/2;
                } 
                bias=moveR(s1,s2);
                s1+=bias;
                s2-=bias;

            }else{
                return nums1[s1];
            }
        }
    }

    private int moveL(int len1,int len2){
        int bias=len1/2;
        if( bias > ( this.nums2 - len2 )/2 ) bias=( this.nums2 - len2 )/2;
        if( bias == 0) return 1;
        if( this.moving > bias ){
            this.moving=bias;
            return bias;
        }else{
            this.moving/=2;
            return this.moving;
        }
    }
    private int moveR(int len1,int len2){
        int bias=len1/2;
        if( bias > ( this.nums2 - len2 )/2 ) bias=( this.nums2 - len2 )/2;
        if( bias == 0) return 1;
        if( this.moving > bias ){
            this.moving=bias;
            return bias;
        }else{
            this.moving/=2;
            return this.moving;
        }
    }
}