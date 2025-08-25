import comp1110.lib.*;
import comp1110.lib.Date;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*;

// 想要求一组平均数，首先需要确定数据类型，为double类型，构造函数！方法名，方法体，变量名！先求链表长度，链表的总数和构造平均数函数以及方法体实现
// 递归首先考虑终止的条件或边界的问题，什么时候递归结束！
int len(ConsList<Integer>list){
    return switch(list){
         case Nil<Integer>_->0;
    case Cons<Integer>(var first,var rest)->1+len(rest);
      };
}
int sum(ConsList<Integer>list){
    return switch(list){
        case Nil<Integer>_->0;
        case Cons<Integer>(var first,var rest)->first+sum(rest);
    };
}
// 先定义两个函数总和的结果和总长度的结果，然后实现返回的类型是什么，最后实现数学逻辑
double average(ConsList<Integer>list){
    int sumresult=sum(list);
    int lenresult=len(list);
    return (double) sumresult/lenresult;
}
void main (){
    ConsList<Integer> list1=MakeList(1,2,3);
    println(average(list1));
   ConsList<String> list3= MakeList("gh","das");
   println(Length(list3));
   ConsList<String> list4=MakeList("a","b","np");
   println(firstString(list4));
   ConsList<Double> list5 =MakeList(1.0,2.0,3.0);
   println(length(list5));
   String s ="2";
   println(tryStringToInt(s));
   ConsList<String> sb=MakeList("comp","6710");
   println(concatenateString(sb));

   ConsList<Integer> ints=MakeList(1,3,3,4,2);
   println( removeint(ints,4)+"这是移除");

      ConsList<Integer> maxs=MakeList(-5);
   println(maxint(maxs)+"这是最大的整数");
   println(reverse(MakeList(1,2,3)));
println(reversetwo(MakeList(1,2,3)));
     println(pinjiezi(MakeList("hello","","World")));
 println(Map(n->ToString(n),MakeList(1,2,3)));
ConsList<Integer> min=MakeList(1,2,3,4,0,-1);
println(getMax(MakeList(-1,-3))+"最大");
    println(getMin(min));
    println(Map(n->n*n,MakeList(1,2,3)));
    println(Filter(n->n%2==0,MakeList(1,2,3,4)));
    println(uniqueSorted(MakeList(1,2,2,3,3,4,4,5)));
    println(pairsum(MakeList(1,2,3,4),MakeList(3,4,5,5)));
    println(fltten(MakeList(MakeList(1,2,3,4))));
    println(allIntegersEqual(MakeList(1,1,1,2)));
    println(eqList(MakeList(),MakeList()));
    println(multiplyByNextIntegers(MakeList(1,2,3,4))+"是每位相乘");
    println(divide(MakeList(1.0,2.0,3.0,4.0)));
    println(sumint(MakeList(1,3,4))+"这是高阶求和");
    println(reversestring(MakeList("hello")));
    println(factorial(4));
    println(sumPositive(3));
    println(sumPositiveInterval(3,5));
    println(repeat(2,"hellp"));
    println(pinjiezifu(MakeList("23","3")));
    println(lastTwo(MakeList(1,2,3,4,5)));
    println(lastOneAsList(MakeList(1,2,3,4,5)));

}
// 求一个字符串的长度使用递归,注意第一个元素要取出来，不能直接递归跟剩下的元素，必须先提取出来因此是Lenth这个fava函数!

int Lengthcount(ConsList<String> stringlist){
  return Fold((String s,Integer acc) ->Length(s)+acc,0,stringlist);
    // return switch(stringlist){
    //     case Nil<String>_->0;
    //     case Cons <String>(var first,var rest)-> Length(first)+Lengthcount(rest);
    // };
}

// *In a file `FirstString.java`*
// Write a function, `firstString`, that given a list of strings, returns the first 
// element of the list. The input list might be empty. Thus, the return type of 
// the function should be `Maybe<String>`. 
// Some examples for such a function are as follows:

// - Given `["COMP1110", "COMP1140", "COMP6710"]`, expect `Something<String>("COMP1110")`.
// - Given `[]`, return `Nothing<String>()`.
// 首先确定返回类型，以及函数的目的，实现递归的终止条件是什么！，返回值是something 和nothing因此要生成新的nothing和something！
// 如果只是采用first会报错，因为是字符串无法转换成maybe，因此需要进行声明转回Maybe
Maybe firstString(ConsList<String>firstringlist){
    return switch (firstringlist){

        case Nil<String>_->new Nothing<String>();
        case Cons<String>(var first,var rest)->new Something<String> (first);
    };
}
// ## Exercise 3 - calculate the length of a list of `Double`s

// *In a file `LengthDoubles.java`*

// Write a **recursive** function, `lengthDoubles`, that given a list of `Double`s, returns the length
// of the list as an `int`. The input list might be empty, and in such a case, the function should return zero.
// Some examples for such a function are as follows:

// - Given `[3.2, 4.5, 7.3]`, expect `3`.
// - Given `[]`, expect `0`.

// Following the design recipe (i.e., step 6), write tests for such a function.
// See the tests for Exercise 2 above, and those for the exercises below
// as a guide on how to write tests with lists. 
// 计算长度为什么加一，因为递归层层嵌套类似于集合，最后的一个元素有一个 因此要加上一
double length(ConsList<Double> doublelen){
    return switch(doublelen){
        case Nil<Double>_->0.0;
        case Cons<Double>(var first,var rest)->1+length(rest);
    };
}
// 首先实现密封接口进行封装起来实现，必须提前定义实现什么类型！（maybe里面的泛型可以是任何的数据类型）
sealed interface MaybeInt permits SomeInt,NoInt{}
record SomeInt(int s)implements MaybeInt{}
record NoInt()implements MaybeInt{}
// 字符串转变为整数类型，先进行判断是什么类型，返回值应该是什么，然后进行方法体的实现，调用fava库可以实现字符串转成整数StringToInt
MaybeInt tryStringToInt(String str){
    if(IsIntString(str)){
        return new SomeInt(StringToInt(str));
    }else {
        return new NoInt();
    }
}
// ## Exercise 4 - concatenate all the strings in a list of `String`s

// *In a file `ConcatenateStrings.java`*

// Write a **recursive** function, `concatenateStrings`, that given a list of strings, returns the result
// of concatenating all the strings in the list. If the input list is empty, the function 
// should return the empty string.

// Some examples for such a function are as follows:

// - Given `["COMP1110", "COMP1140", "COMP6710"]`, expect `"COMP1110COMP1140COMP6710"`.
// - Given `["abc"]`, expect `"abc"`
// - Given `[""]`, expect `""`

// Following the design recipe (i.e., step 6), write tests for such a function.
// See the tests for Exercise 2 above, and those for the exercises below
// as a guide on how to write tests with lists. 
// 进行拼接字符串，首先判断什么数据类型，是字符串类型所以是String做为基本数据类型，进行方法名命名！方法体，以及什么时候结束语句运行，
String concatenateString(ConsList<String>constr){
    return switch(constr){
        case Nil<String>_->"";
        case Cons<String>(var first,var rest)->first+concatenateString(rest);
    };
}


// ## Exercise 6 -  find the largest element in a list of `Integer`s

// *In a file `LargestInteger.java`*

// Write a **recursive** function, `largestInteger`, that given a list of `Integer`s,
// returns the largest element in the list as an `Integer`. You can assume that the 
// input list will have at least one element.

// Some examples for such a function are as follows:

// - Given `[3, 1, 7, 2]`, expect `7`.
// - Given `[4, 5, 3, 4]`, expect `5`.
// - Given `[-1,-2]`, expect `-1`.
// 确定函数的类型是int 类型，使用递归的方法实现，那就是用Conslist链表实现，终止的条件是什么是0；
// 为什么递归，首先需要确定头元素跟谁递归，是不是跟剩下的所有的元素进行比较，这样才能实现，但是函数又不能第一个元素直接跟list比较，所以需要变成list，因此是头元素+方法名剩下的所有元素
int maxint(ConsList<Integer>maxlist){
    return FoldLeft((Integer x ,Integer acc )->Max(x,acc),0,maxlist);
    }


// 反转数字的题目reverse
// 思路：先要进行边界的判断，终止的条件是什么，如何让最后一个元素到一位去，首先列表为空则为Nil,不是的话继续遍历列表
// 使用Append，拼接两个list，方法名+递归（参数），first，因为first是第一位元素因此要创建一个MakeList，限制参数作为list
static <T >ConsList<T>reverse(ConsList<T>reverselist){
    return IsEmpty(reverselist)?new Nil<>():Append(reverse(Rest(reverselist)),MakeList(First(reverselist)));
}
static <Integer>ConsList<Integer> reversetwo(ConsList<Integer>reversetwolist){
    return switch(reversetwolist){
        case Nil<Integer>_->new Nil<>();
        case Cons<Integer>(var first,var rest)->Append(reversetwo(rest),MakeList(first));
    };

}
ConsList<String>reversestring(ConsList<String>list){
  return IsEmpty(list)?new Nil<>():Append(reverse(Rest(list)),MakeList(First(list)));
}
// 用高阶函数拼接字符串 Fold 语法定义初始值，还有累计值acc
String pinjiezi(ConsList<String>pinlist){
    return Fold((String x,String acc)-> x+acc,"",pinlist);
}
// 获取最大值和最小值
int getMax(ConsList<Integer>getmaxlist){
    return Fold((Integer x, Integer acc)->Max(x,acc),First(getmaxlist),Rest(getmaxlist));
}
int getMin(ConsList<Integer>getMinlist){
    return Fold((Integer s ,Integer acc)->Min(s,acc),First(getMinlist),Rest(getMinlist ));
}


// 排序和去重！构造辅助函数先定义一个Conslist，先进行排序！
// 构造辅助函数判断两个类型返回值，第一个是列表为空返回什么，第二个rest为空返回什么！
// 进行递归先定义两个整数类型的变量，第一个作为list里面的第一个元素，第二个作为rest 里面的第一个元素，限制里面的参数都是sortlist
// 
ConsList<Integer>uniqueSorted(ConsList<Integer>sortlist){
    ConsList<Integer> sorted =Sort(sortlist);
    return uniq(sorted);
}
ConsList<Integer>uniq(ConsList<Integer>sortlist){
    if (IsEmpty(sortlist)){return sortlist;}
    if (IsEmpty(Rest(sortlist))){return sortlist;}
Integer a = First(sortlist);
Integer b =First(Rest(sortlist));
return Equals(a,b)?uniq(Rest(sortlist)):new Cons<>(a,uniq(Rest(sortlist)));
}
// 成对相加的问题，
// 用map函数进行映射，每个元素都相加！
ConsList<Integer>pairsum(ConsList<Integer>list1,ConsList<Integer>list2){
    return Map((Integer a,Integer b)->a+b,list1,list2);
}
// 嵌套列表的展开，如何展开列表，依然用Append，
ConsList<Integer>fltten(ConsList<ConsList<Integer>>xss){
    return IsEmpty(xss)? new Nil<>():Append(First(xss),fltten(Rest(xss)));
}
// ## Exercise 5 - remove an element from a list of `Integer`s 

// *In a file `RemoveInteger.java`*

// Write a **recursive** function, `removeInteger`, that given a list of `Integer`s and an `Integer`, 
// returns a list of `Integer`s where the first occurrence of that `Integer` in the list is removed. 
// If such element is not present, return the list unchanged.
// Recall that we do not compare values of type `Integer` using the `==` operator,
// but the `Equals` function in the standard functional Java library.

// Some examples for such a function are as follows:
// - Given `[3, 2, -1, 2]`, and `2`, expect `[3, -1, 2]`.
// - Given `[3, 2, -1, 2]`, and `0`, expect `[3, 2, -1, 2]`.
// - Given `[]`, and `-2`, expect `[]`.
// 这是一个移除特定的数字，这个数字是整数，如何考虑递归呢？
// 首先需要判断递归终止的条件，以及判断是什么函数类型，第二需要键入一个新的整数类型作为参数，用函数Equal比较第一个元素和键入的整数比较，一样的话执行剩下的所有数字，不一样的话执行递归，因为是cons 链表必须是cons 第一个元素和方法名剩下的元素和整数i
// 用头元素和处理过的尾巴构造一个新的链表！

ConsList<Integer> removeint(ConsList<Integer>removein,int i){
    // return switch(removein){
    //     case Nil<Integer>_->new Nil<>();
    //     case Cons<Integer>(var first,var rest)->Sort(Equals(first,i)? rest:new Cons<>(first,removeint(rest,i)));
    // };
    if(IsEmpty(removein)){return new Nil<>();}
    return Equals(First(removein),i)?Rest(removein):new Cons<>(First(removein),removeint(Rest(removein),i));
}
ConsList<Integer>uniqueSortedd(ConsList<Integer>sortedlist){
    ConsList<Integer> sorteds =Sort(sortedlist);
    return uniqq (sortedlist);
}
ConsList<Integer>uniqq(ConsList<Integer>sortedlist){    
    if(IsEmpty(sortedlist)){return sortedlist;}
    if(IsEmpty(Rest(sortedlist))){return sortedlist;}
    Integer c =First(sortedlist);
    Integer d = First(Rest(sortedlist));
    return Equals(c,d)?uniqq(Rest(sortedlist)):new Cons<>(c,uniqq(Rest(sortedlist)));

}
ConsList<Integer>parwisesum(ConsList<Integer>list3,ConsList<Integer>list4)
{
    return Map((Integer a, Integer b)->a+b,list3,list4);
}
ConsList<Integer>pair(ConsList<ConsList<Integer>>Consint){
    return IsEmpty(Consint)?new Nil<>():Append(First(Consint),pair(Rest(Consint)));
}
// ## Exercise 7 - determine if all elements in a list of `Integer`s are equal
// *In a file `AllIntegersEqual.java`*
// Write a function, `allIntegersEqual`, that given a list of `Integer`s,
// returns `true` if all elements are equal, and `false` otherwise. If the function receives
// an empty list, it returns `true`.
// As always, we do not compare values of type `Integer` using the `==` operator,
// but the `Equals` function in the standard functional Java library.
// Hint: `Length(list)` returns the number of elements in a list. If the list is empty, it returns zero.
// Hint: see also hints for Exercise 6.
// 进行这个操作可以使用纯递归，例如什么情况下终止递归，其一是列表为空，其二是剩下的列表也为空，因此需要
boolean allIntegersEqual(ConsList<Integer>eqlist){
    if(IsEmpty(eqlist)){return true;} 
    if (IsEmpty(Rest(eqlist))){return true;}
      return Equals(First(eqlist),First(Rest(eqlist)))&&allIntegersEqual(Rest(eqlist));
    
    }
// ## Exercise 8 - determine if two lists of `Integer`s are equal
// *In a file `IntegerListsEqual.java`*
// Write a **recursive** function, `integerListsEqual`, that given two lists of `Integer`s,
// returns `true` if they have elements with the same values in the same order, 
// and `false` otherwise.
// As always, we do not compare values of type `Integer` using the `==` operator,
// but the `Equals` function in the standard functional Java library.
// Some examples for such a function are:
// * Given: `[]`, `[]`;  Expect: `true`
// * Given: `[1,2,3]`, `[1,2,3]`; Expect: `true`
// * Given: `[1,2,3]`, `[1,2,2]`; Expect: `false`

boolean eqList(ConsList<Integer> list5,ConsList<Integer>list6){
    return Equals(list5,list6);
}

// ## Exercise 11 - multiply by next

// *In a file `MultiplyByNextIntegers.java`*

// Design a function `multiplyByNextIntegers` that given a list of `Integer`s, 
// returns a new list which contains every number in the input list multiplied
// by the number that comes right after it, and does not have the last element
// of the input list. If the input list has only one element, returns an empty
// list. You may assume that the input list has at least one element.

// Some examples of this function are as follows:

//  * Given: `[1]`; Expect: `[]`
//  * Given: [1,2,3,4]; Expect: `[1*2, 2*3, 3*4] = [2, 6, 12]`

// Hint: the `Length`, `First` and `Rest` list functions from the standard functional 
// Java library might be useful.

// Some tests for this function are as follows:
ConsList<Integer> multiplyByNextIntegers(ConsList<Integer> list) {
    // |list| < 2 -> []
    if (IsEmpty(list) || IsEmpty(Rest(list))) {
        return new Nil<>();
    }
    Integer a = First(list);
    Integer b = First(Rest(list));
    return new Cons<>(a * b, multiplyByNextIntegers(Rest(list)));
}
// ## Exercise 12 - divide by previous

// *In a file `DivideByPreviousDoubles.java`*

// Design a function `divideByPreviousDoubles` that given a list of `Double`s, 
// returns a new list which contains every number in the input list dividing
// by the number that comes right before it, and does not have the first element
// of the input list. If the input list has only one element, returns an empty
// list. You may assume that no elements in the input list are equal to zero.

// Some examples of this function are as follows:

//  * Given: `[1]`; Expect: `[]`
//  * Given: [1,2,3,4]; Expect: `[2/1, 3/2, 4/3] = [2, 1.5, 1.333]`

// Hint: the `Length`, `First` and `Rest` list functions from the standard functional 
// Java library might be useful.
ConsList<Double>divide(ConsList<Double>doublelist){
    if(IsEmpty(doublelist)||IsEmpty(Rest(doublelist))){return new Nil<>();}
    Double m =First(doublelist);
    Double n=First(Rest(doublelist));
    return new Cons<>(m/n,divide(Rest(doublelist)));
}
// ## Exercise 1 - squaring the elements in a list of `Integer`s

// *In a file `SquareIntegerList.java`* 

// Write a function, `squareIntegerList`, that given a list of `Integer`s, i.e., `ConsList<Integer>`,
// returns a list of `Integer`s with the elements of the input list squared. 

// Some examples of this function are as follows:

//   * Given: `[]`; Expect: `[]`
//   * Given: `[1,2,3,-4,5]`; Expect: `[1*1, 2*2, 3*3, -4*-4, 5*5]` = `[1,4,9,16,25]`

// Your function's body should only contain a single `return` statement.

// Hint: the lambda expression `x->x*x` combined with a call to the `Map` higher-order function may help to solve this exercise
ConsList<Integer>square(ConsList<Integer>squarelist){
    if (IsEmpty(squarelist)){return new Nil<>();}
    return Map(x ->x*x,squarelist);
}
int sumint(ConsList<Integer>sumlist){
    if(IsEmpty(sumlist)){return 0;}
    return FoldLeft((Integer c , Integer f)->c+f,0,sumlist);
}
// ## Exercise 1 - Factorial

// *In a file `Factorial.java`*

// Write a **recursive** function, `factorial`, that given an input positive integer number 
// (i.e., an `int`), returns as an `int` the factorial of the input `int`.

// The factorial of `1` is `1`. The factorial of a number larger than one, `n>1`, is defined as the
// product of `n` and the factorial of `n-1`.

// Some examples are as follows:

// * Given: `1`; Expect: `1`
// * Given: `2`; Expect: `1*2=2`
// * Given: `4`; Expect: `1*2*3*4=24`
int factorial(int n) {
    if (n == 1) {
        return 1;                      // 基础情况
    } else {
        return n * factorial(n - 1);   // 递归情况
    }
}
ConsList<Integer>fibList(int n ){
    return reverse(fibAcc(n,0,1,new Nil<>()));
}
ConsList<Integer>fibAcc(int k, int a,int b ,ConsList<Integer>acc){
    return k==0?acc:fibAcc(k-1,b,a+b,new Cons<>(a,acc));
}
// 统计字符频率
//  ConsList<Pair<Character,Integer>>countChar(String s ){
//     int n =Length(s);
//     ConsList<Integer>idxs=BuildList(n,i->i);
//     return FoldLeft((Integer i,ConsList<Pair<Character,Integer>>icc)->{
//         char c = GetCharAt(s,i);
//         int newV=Default(Map(Get(icc,i),(Integer oldeValue)->oldeValue+1),1);
//         return Put(icc,i,newV);
//     } new Nil<>();idxs;
//     );
//     }
// 二叉树
sealed interface Tree permits Node ,Leaf{}
record Leaf(int value) implements Tree{}
record Node(int value,ConsList<Tree>children )implements Tree{}
// 计算树的高度
int height(Tree t) {
    return switch (t) {
        case Leaf l -> 1;
        case Node n -> {
            int mx = FoldLeft(
                (Tree child, Integer acc) -> Max(acc, height(child)),
                0,
                n.children()
            );
            yield 1 + mx;
        }
    };
}
// 计算树叶数量
int countLeaves(Tree t){
    return switch(t){
        case Leaf l -> 1;
        case Node n -> FoldLeft((Tree child ,Integer acc )->acc +countLeaves(child),0,n.children);
    };
}
// 求所有叶子节点的和
int sumLeaves(Tree t ){
    return switch(t){
    case Leaf l ->l.value();
    case Node n -> FoldLeft((Tree child,Integer acc   )->acc+sumLeaves(child),0,n.children);
};
}
// ## Exercise 2 - Sum positive numbers

// *In a file `SumPositive.java`*

// Write a **recursive** function, `sumPositive`, that given a integer number `n` (`int`) greater or equal than one, 
// returns an integer with the sum of the first `n` positive integer numbers. 

// Some examples are as follows:

// * Given: `1`; Expect: `1`
// * Given: `2`; Expect: `1+2=3`
// * Given: `3`; Expect: `1+2+3=6`
int sumPositive(int n ){
    if(n==1){return 1;}
    else {return n + sumPositive(n-1);}
}

// ## Exercise 3 - Sum positive numbers within an interval

// *In a file `SumPositiveInterval.java`*

// Write a **recursive** function, `sumPositiveInterval`, that given two positive numbers `n` and `m`, with `n` being smaller or equal than `m`,
// returns the sum of all numbers larger or equal than `n` and smaller or equal than `m`.
 
// Some examples are as follows:

// * Given: `2` and `2`; Expect: `2`
// * Given: `3` and `5`; Expect: `3+4+5=12`
int sumPositiveInterval(int n ,int m ){
    if (n>m){return 0;}
    if (m==n){return n ;}
    return n+sumPositiveInterval(n+1,m);
}
// *In a file `Repeat.java`*

// Write a **recursive** function, `repeat`, that given an `String` and an integer
// number `n` (`int`) greater or equal than zero, returns a `String` with `n` copies
// of itself. If `n` is zero, then the function must return the empty string.
// * Given: `"wow"` and `0`; Expect: `""`
// * Given: `"comp1110"` and `1`; Expect: `"comp1110"`
// * Given: `"hello"` and `3`; Expect: `"hellohellohello"`
// * Given: `"comp1110/1140/6710 "` and `5`; Expect: `"comp1110/1140/6710 comp1110/1140/6710 comp1110/1140/6710 comp1110/1140/6710 comp1110/1140/6710 "`
String repeat(int n,String str ){
    if (n==0){return "";}else {return str+repeat(n-1,str);}
}


String pinjiezifu(ConsList<String>strs){
    if(IsEmpty(strs)){return "";}
    return Fold((String z,String zcc)->z+zcc,"",strs);
}
// 返回倒数两位数字组成的列表
ConsList<Integer> lastTwo(ConsList<Integer> list) {
    return switch (list) {
        case Nil<Integer> _ -> new Nil<>(); // 空表返回空
        case Cons<Integer>(var first, var rest) when IsEmpty(rest) ->
            new Nil<>(); // 只有一个元素，也返回空
        case Cons<Integer>(var first, var rest) when IsEmpty(Rest(rest)) ->
            MakeList(first, First(rest)); // 刚好两个元素，直接返回
        case Cons<Integer>(var first, var rest) ->
            lastTwo(rest); // 否则递归向后找
    };
}
// 打印最后一个元素
ConsList<Integer> lastOneAsList(ConsList<Integer> list) {
    return switch (list) {
        case Nil<Integer> _ -> new Nil<>();
        case Cons<Integer>(var first, var rest) when IsEmpty(rest) ->
            MakeList(first); // 只剩一个元素，返回单元素列表
        case Cons<Integer>(var first, var rest) ->
            lastOneAsList(rest); // 继续递归
    };
}
