import comp1110.lib.*;
import comp1110.lib.Date;
import static comp1110.lib.Functions.*;

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
   ConsList<Integer> ints=MakeList(1,2,3,4);
   println( removeint(ints,0));
      ConsList<Integer> maxs=MakeList(1,2,3,4);
   println(maxint(maxs));
}
// 求一个字符串的长度使用递归,注意第一个元素要取出来，不能直接递归跟剩下的元素，必须先提取出来因此是Lenth这个fava函数!

int Lengthcount(ConsList<String> stringlist){
    return switch(stringlist){
        case Nil<String>_->0;
        case Cons <String>(var first,var rest)-> Length(first)+Lengthcount(rest);
    };
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
    return switch(removein){
        case Nil<Integer>_->new Nil<>();
        case Cons<Integer>(var first,var rest)->Equals(first,i)? rest:new Cons<>(first,removeint(rest,i));
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
    return switch(maxlist){
        case Nil<Integer>()->0;
        case Cons<Integer>(var first,var rest)->Max(first,maxint(rest));
    };
}
