--phython
好的,给您列出100道Python面试题,由浅入深,并附上答案:

1. 什么是Python?

Python是一种 Interpreter 解释型语言,或者又能说成是交互式、面向对象、动态数据类型的高级编程语言。Python是由Guido van Rossum在1989年发明,第一个公开发行版发行于1991年。

2. Python的特征和优点?

- 简单易学,语法简单清晰,阅读代码容易
- 动态类型语言,无需事先声明变量类型
- 支持多编程范式,如面向对象、函数式、命令式等
- 开源免费,社区活跃,拥有大量优秀的三方库
- 解释型语言,无需编译运行,开发调试效率高
- 高级语言,支持各种系统调用和内存管理,移植性好

3. Python常用的数据类型有哪些?

数字(Number)、字符串(String)、列表(List)、元组(Tuple)、字典(Dictionary)、集合(Set)等。

4. Python中的变量命名规则?

变量名只能包含字母、数字和下划线,不能以数字开头,不能使用关键字和函数名,严格区分大小写。

5. Python如何实现多行语句?

可以使用反斜杠 \ 或圆括号 () 实现多行语句。也可以使用三个单引号 ''' 或三个双引号 """ 将多行字符串赋值给一个变量。

6. Python中的注释方法有哪些?

单行注释使用 # ,多行注释使用三个单引号 ''' 或三个双引号 """ 。

7. Python中if-else语句的基本语法?

```python
if condition:
    statements
elif condition:
    statements  
else:
    statements
```

8. Python中for循环的语法是什么?

```python
for 变量 in 集合:
    语句
```

9. Python中while循环的语法是什么?

```python  
while 条件语句:
    执行语句
```

10. Python中break、continue、pass语句的作用是什么?

- break: 跳出当前循环
- continue: 跳出本轮循环,继续下一轮循环
- pass: 空语句,一般用于占位

11. Python中定义函数的基本语法是?

```python
def 函数名(参数):
    函数体
```

12. Python中函数的参数传递机制是什么?

Python函数参数以传引用的形式传递,即在函数内部修改参数会影响到外部变量。

13. Python中的可变和不可变对象有哪些?

- 可变对象:列表、字典、集合
- 不可变对象:数值、字符串、元组

14. Python中列表的常见操作有哪些?

- 添加: append、extend、insert
- 删除: remove、pop
- 修改: 赋值
- 查找: index、count、in
- 排序: sort、sorted、reverse

15. Python中的切片操作是什么?

```python
L[start:stop:step]
```

start为起始下标,stop为结束下标,step为步长,可以实现对列表的切片操作。

16. Python中的字典的常见操作?

- 添加/修改: d[key] = value
- 删除: del d[key] 或 d.pop(key)
- 查找: d[key] 或 d.get(key)
- 遍历: for k in d 或 for k,v in d.items()

17. Python中如何删除字典或集合中元素?

- 删除字典元素:del d[key] 或 d.pop(key)
- 删除集合元素:s.remove(obj)或s.discard(obj)

18. Python中的装饰器是什么?作用有哪些?

装饰器是一个函数,用于修改、扩展其他函数的功能,而不需要直接修改被装饰的函数源代码。常见作用:日志、访问控制、缓存、测量时间等。

使用 @ 符号应用装饰器,放在函数定义前一行。

19. Python中三个标准文件对象是什么?

- stdin:标准输入
- stdout:标准输出
- stderr:标准错误

20. Python中异常处理的基本语法是什么?

```python
try:
    可能发生错误的代码
except:
    如果发生异常,执行的代码
```

try代码块中发生异常会执行except代码块,如果没有异常,不执行except代码块。

21. Python的列表推导式可以用来干什么?

可以用更简洁的语法快速生成列表,例如:

```python
[x**2 for x in range(10)] 
```

22. 解释 Python 中 pass 语句的作用?

pass语句用作占位符,相当于一个空操作。当语法上需要语句但逻辑不需要执行任何操作时可以使用pass,保持程序结构的完整性。

23. 如何在 Python 中定义常量?

可以用全部大写的变量名表示常量,通过导入 constants 模块定义常量,或者使用 functools 模块的 @lru_cache 装饰器模拟常量。

24. Python 中的切片语法是什么?

Python中的切片语法如下:

```python
变量[start:stop:step]
```

start为起始下标,stop为结束下标,step为步长。

25. Python 中的 LEGB 规则是什么?

LEGB 指的是 Python 中的作用域(scope)的顺序规则:

- L: Local 函数内的命名空间
- E: Enclosing 嵌套函数的命名空间
- G: Global 模块的命名空间
- B: Built-in Python内置模块的命名空间

按照顺序 LEGB 查找变量。

26. Python中的is和==有什么区别?

is用于比较对象的内存地址,==用于比较对象的值。当比较基本数据类型时,两者等价。对于容器类型如 list、dict,应该使用 == 判断元素值而不是 is 判断内存地址。

27. Python中如何拷贝一个对象?

使用 copy 模块的 copy 或 deepcopy 函数可以拷贝对象。调用对象的 copy 方法也可以拷贝对象。

28. Python中eval和exec的区别是什么?

- eval: 将字符串当成有效的表达式来求值并返回计算结果。
- exec: 将字符串作为 Python 代码执行。

29. Python的基本数据结构有哪些?

数字、字符串、列表、元组、字典、集合。

30. 请解释 Python 中的 GIL 是什么?

GIL 是 Python 的全局解释器锁(Global Interpreter Lock)。它使 Python 在同一时刻只有一个线程在执行,防止同时修改相同对象导致冲突。GIL 限制了 Python 多线程程序的并行性。

31. Python中如何实现深拷贝和浅拷贝?

- 浅拷贝: 直接赋值、slice、dict.copy()
- 深拷贝: copy.deepcopy(),利用递归复制对象内部的子对象

32. Python的交换两个变量的标准方法?

使用元组打包和解包交换变量:

```python
a, b = b, a
```

33. Python中如何根据字典的值对字典进行排序?

使用 sorted 和 lambda 函数:

```python
sorted(d.items(), key=lambda x:x[1])  
```

34. Python 中如何获取对象的类型?

使用 type() 函数或 isinstance() 函数判断类型。也可以直接使用 __class__ 属性。

35. Python列表和数组有什么区别?

数组存储单一类型数据,列表可以存储不同类型数据。数组元素在内存中连续存储,列表元素在内存任意位置。数组更适合数值计算,列表更适合存储、遍历。

36. Python中内置的数据结构有哪些?

列表、元组、字典、集合。

37. 解释 Python 中的闭包概念。

闭包指延伸了作用域的函数,它可以访问定义函数时存在的非全局变量。要创建闭包必须至少满足以下条件:

- 必须有一个内嵌函数
- 内嵌函数必须引用外部函数中的变量
- 外部函数必须返回内嵌函数

38. Python 中的装饰器原理是什么?

装饰器本质上是一个函数,可以让其他函数在不需要做任何代码变动的前提下增加额外功能,装饰器的返回值也是一个函数对象。使用@语法将装饰器置于另一个函数定义之前实现装饰功能。

39. Python 中生成器的作用是什么?

生成器可以用来创建按需迭代的数据流,节省大量内存空间,实现延迟计算。在 Python 中,包含 yield 关键字的函数就是生成器。

40. Python 中集合和列表的区别是什么?

- 集合存储不重复且无序的元素,列表元素可重复且有顺序
- 列表可包含不同类型元素,集合只能包含 hashable 对象
- 列表支持索引、切片操作,集合不支持
- 集合支持高效成员检测,列表需要线性扫描

41. Python 如何实现单例模式?

使用装饰器或元类实现单例模式。核心思想是控制实例化操作,只允许创建一个实例。

```python
class Singleton(type):
    _instances = {}
    def __call__(cls, *args, **kwargs):
        if cls not in cls._instances:
            cls._instances[cls] = super(Singleton, cls).__call__(*args, **kwargs)
        return cls._instances[cls]
        
class MyClass(metaclass=Singleton):
    pass

```

42. 解释 Python 中的反迭代器和生成器的区别

反迭代器可以无限遍历数据元素而不需要存储整个数据流。生成器也是一种迭代器,区别在于生成器需要用户调用 next() 来获取下一个数据,更加灵活。

43. Python 中的字符串格式化有哪几种方式?

- % 运算符格式化
- str.format() 方法格式化
- f-string 格式化

44. Python 中多线程共享数据需要注意什么?

由于 GIL 的限制,Python 中多线程对于 CPU 密集型任务没有优势。多线程之间共享数据需要用锁保护,否则存在数据竞争和死锁的问题。

45. 请简述 Python 中 asyncio 模块的作用和实现原理。

asyncio模块实现了使用协程的异步编程框架。它基于事件循环,可以实现非阻塞的并发。协程通过await让出控制权,异步执行任务而不阻塞主线程。

46. Python 中的装饰器(decorator)是什么?有什么作用?

装饰器本质上是一个函数,可以让其他函数在不需要做任何代码变动的前提下增加额外功能,装饰器的返回值也是一个函数对象。使用@语法将装饰器置于另一个函数定义之前实现装饰功能。

常见用途有:插入日志、性能测试、访问控制、缓存等。

47. 说说 Python 中的 GIL 锁和它的影响。

GIL是Python的全局解释器锁,使同一时刻仅有一个线程执行,实现了解释器级别的同步。

GIL的存在确保了线程安全,但也同时限制了Python多线程程序的并发性。在CPU密集型任务中表现明显。

48. Python中如何合并多个字典?

使用 {} 创建新字典,并使用 update 方法从其他字典更新:

```python 
dict1 = {'a':1, 'b':2}
dict2 = {'b':3, 'c':4}
dict3 = {}
dict3.update(dict1)
dict3.update(dict2)
# {'a': 1, 'b': 3, 'c': 4}
```

也可以使用 ** 运算符,后面的字典会覆盖前面字典的同名键:

```python
dict3 = {**dict1, **dict2} 
```

49. 解释一下 Python 中的切片操作。

Python 切片语法:

```
序列[start:stop:step]
```

- start:起始下标,默认为0
- stop:结束下标(不包含)
- step:步长,默认为1

切片返回区间内的元素组成的新序列,包前不包后。

50. Python 中的 LEGB 规则是什么?

LEGB 是 Python 中的作用域(scope)顺序规则:

- L - Local:函数内的作用域
- E - Enclosing:外围嵌套函数内的作用域
- G - Global:模块内定义的全局作用域
- B - Built-in:Python内置模块中的作用域

按照这个规则顺序查找变量。

51. 列表推导式和生成器表达式的区别是什么?

列表推导式会直接产生列表,而生成器表达式会返回一个生成器对象,需要迭代获取元素。

生成器不需要生成和保存所有元素,所以更节省内存空间。

52. Python 中如何实现属性可修改的只读属性?

可以通过 property 装饰器实现:

```python
class Person:
  @property
  def name(self):
    return self._name
  
  @name.setter
  def name(self, value):
    self._name = value
```

name 变成可读性质的属性,而设置 name 会调用 setter 方法。

53. Python 中变量的作用域有哪几种?

Python 中变量有4种作用域:

- L - Local: 函数内定义的变量
- E - Enclosing: 嵌套函数内定义的变量
- G - Global: 模块中定义的变量
- B - Built-in: Python内置变量名

按 LEGB 顺序查找规则定位变量。

54. Python中如何动态获取和设置对象属性?

通过内置函数 getattr() 和 setattr() 可以实现动态获取和设置对象属性。

getattr(obj, 'attr') 相当于 obj.attr
setattr(obj, 'attr', value) 相当于 obj.attr = value

55. 解释一下 Python 中的元类(metaclass)。

元类是创建类对象的类。元类可以 intercept 类的创建,修改类的定义,返回修改后的类。

type 就是一个常见的元类。使用元类可以实现单例模式、日志装饰器等功能。

定义类时通过 __metaclass__ 指定使用的元类。

56. 请解释 Python 中的 MRO(方法解析顺序)。

MRO 指的是多重继承情况下,对方法的搜索顺序。它保证从子类开始,依次向父类方向寻找,遵循C3线性化算法。

通过 __mro__ 属性可以查看类的 MRO。

57. 简述 Python 中的 Pickle 模块的作用。

Pickle 实现了对象序列化,可以将Python对象转换为字节流,存入文件或数据库,后续再解析恢复对象。这样就实现了对对象持久化存储。

58. Python 中的装饰器(Decorator) 有什么作用?

装饰器本质上是一个函数,用来为其他函数或类添加额外功能,而不需要直接修改函数或类的定义。这样做的好处是不修改原函数代码开放封闭原则。

常见用途有:日志等

好的,继续输出:

59. Python中如何实现所有对象都可迭代的需求?

自定义对象需要实现__iter__方法,该方法返回一个迭代器对象;迭代器对象需要实现__next__方法,该方法在被调用时返回下一个可用的元素,如果没有元素了则抛出StopIteration异常。

60. 解释Python中is和==的区别

is用于判断两个对象的身份标识(内存地址),是否指向同一个对象。==用于判断两个对象的值是否相等。

当比较基本数据类型时,is和==作用相同;对于容器类型如list、dict,应该用==判断元素值,而不是is判断内存地址。

61. Python中*args和**kwargs的作用分别是什么?

*args用于将不定数量的位置参数,组装成一个tuple传递给函数。

**kwargs用于将不定数量的关键字参数,组装成一个dict传递给函数。

62. 说一下Python中的可变类型和不可变类型?

不可变类型:数字类型、字符串、元组。对象一旦创建,不能修改自身的属性。

可变类型:列表、字典。对象创建后,仍然可以修改其属性的值。

63. Python中如何在函数内部修改全局变量?

使用global关键字声明变量即可:

```python
count = 0
def add():
    global count
    count += 1
```

64. Python函数中的变量作用域问题,如何访问不同作用域的变量?

Python中变量按LEGB规则查找:

- Local:函数内部作用域
- Enclosing:外层嵌套函数内的作用域
- Global:模块全局作用域
- Built-in: 系统内置作用域

使用global关键字声明可修改全局变量,使用nonlocal声明访问嵌套函数作用域的变量。

65. 什么是面向对象编程?请简述Python中类和对象的关系。

面向对象编程的思想是使用类和对象来组织代码,将数据与操作数据的方法封装在一起。

在Python中,类提供了对象的蓝图,对象就是根据类创建出来的一个具体存在。对象包含特性和方法用来表示数据和操作数据。

66. 在Python中如何给对象属性设置访问限制?

Python对象的属性默认为公开的。要设置私有属性,需要在属性名前加两个下划线__,这样属性就不能被外部访问。

可以通过对象方法访问和修改私有属性。

67. 解释一下Python中新式类和旧式类的区别

在Python2中:
- 新式类:继承了object的类
- 旧式类:没有继承object的类

新式类拥有很多特性如属性描述符、__slots__、 元类等。推荐使用新式类。

Python3中统一要求所有类都继承object,都是新式类。

68. Python中类方法、静态方法和实例方法有何区别?

- 实例方法:第一个参数是self,表示实例本身
- 类方法:第一个参数是cls,表示类本身
- 静态方法:普通函数,没有默认的第一个参数

69. Python中的反射机制是什么?如何实现?

反射就是通过字符串的形式操作对象相关的属性。

主要通过getattr(), setattr(), hasattr()等方法实现反射。

70. Python如何实现重载实例方法?

通过定义类似__method类的方法名称实现重载。传入self和method名称,就可以调用重载实例方法。

71. Python中如何动态创建类?

可以用type函数动态创建类,基本语法如下:

```python
MyClass = type('MyClass', bases类元组,属性字典)
```

还可以用metaclass实现更灵活的类创建。

72. 什么是Python中的元类(Metaclass)?

元类是用来创建类的类。元类可以拦截类的创建,修改类的定义,返回修改后定义的类。

使用metaclass参数定义类及其元类:

```python 
class MyClass(metaclass=MetaClass):
  ...
```

73. 什么是Python中的描述符?如何实现一个描述符?

描述符是一个有绑定行为的对象属性,访问该属性时会调用描述符的方法。

可以通过定义__get__()、__set__()、__delete__()方法实现一个描述符。

74. @property装饰器的作用是什么?

@property装饰器将类方法转换为可读性属性,语法上访问如属性,避免直接暴露方法调用。

设置值时需要通过setter方法。

75. 什么是面向切面编程AOP及其在Python中的实现?

AOP实现程序逻辑的隔离,通过装饰器、元类等在不修改源代码的情况下给程序插入新功能。

Python中的装饰器函数就是一种简单的AOP实现。

76. 说一下Python中单例模式的实现?

使用__new__方法实现单例模式。判断实例存在则直接返回,否则新建实例并存入实例字典中。
同时应该定义__init__方法避免新的实例被创建。

也可以使用装饰器实现单例。

77. Python中怎么实现列表、字典、集合的推导式?

```python
[ expr for value in collection ] 
{ key_expr:value_expr for value in collection}
{ expr for value in collection }
```

用简洁语法根据集合过滤/转换元素来生成列表、字典、集合。

78. 简述Python中的列表comprehension?

列表推导提供了一种更简洁高效创建列表的方法。基本语法:

```python
[ expr for value in collection ]
```

按照一定规则根据集合生成新列表,能避免for循环的繁琐。

79. Python函数的参数传递机制是什么?

Python函数参数以引用传递的方式传递对象。当传入不可变对象时,函数内部无法改变外部对象;但可变对象在函数内改变会影响外部对象。

80. Python中的is 和==区别是什么?

is用于比较对象的身份标识,==用于比较两个对象的值是否相等。

is判断是否同一对象,==判断值是否相等。对不可变类型,两者通常等价。

81. 请简要描述Python中的GIL。

GIL是Python的全局解释器锁,实现了解释器级别的线程同步。GIL确保同一时刻只有一个线程执行,所以多线程在Python中不能利用多核CPU。

GIL的存在确保了线程安全,但也限制了Python多线程的并发性。

82. 什么是闭包?请简述闭包的作用和优点。

闭包是函数和声明函数时的环境状态(Lexical scope)组合的一个整体。闭包能访问定义函数时可用的非全局变量。

好处是避免全局变量污染,缺点是变量始终在内存中,消耗内存。

83. 简述面向对象中方法重载和重写的区别

方法重载指同一个类中定义相同名称不同参数的方法。Python在运行时通过参数区分不同方法。

方法重写指子类实现与父类方法相同的方法,PYTHON会调用子类重写后的方法。

84. Python的装饰器(decorator)功能是什么?

装饰器本质上是一个callable object,作用是在不改变原函数代码的基础上给函数增加新功能。使用@语法将装饰器置于函数定义上方。

常见用途有:插入日志、性能测试、访问控制、缓存等。

85. Python列表(list)的常用方法有哪些?

- 添加:append()、extend()、insert()
- 删除:remove()、 pop()、clear()
- 排序:sort()、reverse()
- 访问:__getitem__()、__setitem__()、__len__()
- 搜索:index()、count()、in

86. Python中如何实现深浅copy?

浅copy: 对容器对象进行切片操作、工厂函数copy.copy()等

深copy: 使用copy.deepcopy()递归拷贝对象内部元素

87. 什么是Python中的slots机制?使用的场景是?

slots是类变量,限定对象只能拥有slots中定义的属性,禁止动态添加。

优点是降低内存开销,提高访问速度;缺点是限制灵活性。

适用于类属性固定、对象数量较多的场景。

88. 简述Python中重载实例方法的实现机制

通过在实例上定义特殊的__method名格式方法实现重载。当调用实例方法时,会优先查找这个特殊方法并调用。

传入self和方法名参数,可调用任意重载方法。

89. @property的作用是什么?

将方法转变为类属性调用,使syntax更优雅。

使用@property装饰getter方法,@方法名.setter装饰setter方法。

90. 列表(list)和元组(tuple)有什么区别?

- 列表可变,元组不可变
- 列表用中括号定义,元组用小括号
- 列表推导式生成新列表,元组无推导式
- 列表可使用增删操作修改,元组不可变

91. 请简述Python中sys模块的主要作用

sys模块主要用于提供系统环境信息,以及与Python解释器交互的函数。

主要功能:

- 获取对象引用:argv、path、modules等
- 获取限制信息:maxsize、maxunicode等
- 执行与解析器的交互操作:exit()、getrefcount()等

92. Python中如何实现计数器(count)?

使用itertools模块的count()函数可以实现计数器功能,每次调用将返回递增的整数:

```python
import itertools
counter = itertools.count()
print(next(counter), next(counter)) # 0 1
```

93. Python的列表排序方式有哪些?各自的时间和空间复杂度是?

- 内置函数sorted():O(nlogn)
- list.sort():O(nlogn)
- sorted(items, key=...): O(nlogn)
- 手动排序算法:选择、冒泡 O(n^2)

94. 解释一下Python中返回局部变量的闭包的用途

返回局部变量的闭包可以保持函数调用过程中局部变量的状态,使得这个状态可维持到闭包存在的期间。

应用场景如:计数器、状态标记等需要在函数调用之间保存信息的情况。

95. 请简要描述Python中的一些魔法方法

- \_\_init\_\_:对象初始化调用方法
- \_\_str\_\_:转换为字符串方法
- \_\_len\_\_:对象长度方法
- \_\_call\_\_:对象调用方法
- \_\_getitem\_\_:实现序列索引访问
- \_\_repr\_\_:生成解析器可读取的表示形式

等魔法方法实现对象的内置语言功能。

96. 简述read、readline和readlines之间的区别

- read(): 读取整个文件内容,返回字符串。
- readline(): 读取下一行,返回字符串。
- readlines(): 读取整个文件,返回行字符串列表。

97. 说一下Python中异常处理的方式

使用try-except代码块进行异常处理:

```python 
try:
  可能发生异常的代码
except ExceptionType:
  出现异常执行代码  
```

try块检测异常,出现异常跳转至相应except块处理。

98. Python函数参数传递的机制是值传递还是引用传递?

Python参数以引用传递的方式传入函数。

在函数内部对不可变对象的修改不影响外部变量,即值传递;对可变对象的修改影响外部变量,即引用传递。

99. 什么是Python中的字典推导式?

字典推导式可以从其他字典或序列创建新字典。语法格式如下:

```python
{key_expr: value_expr for value in collection}
```

根据collection过滤和转换元素,生成字典类型。

100. 简述Python中unittest框架的用法

unittest实现了测试用例集合、运行用例、验证结果功能。主要涉及四个组件:

- TestCase: 定义测试类,包含各测试方法
- TestSuite: 测试集合
- TestRunner: 运行测试用例
- TestLoader: 加载TestCase到TestSuite

编写TestCase测试类,通过TextTestRunner运行测试用例。

===================================================================================

--phython
Okay, let me list 100 Python interview questions for you, from easy to deep, with answers:

1. What is Python?

Python is an interpreter language, or it can be said to be an interactive, object-oriented, dynamic data type high-level programming language. Python was invented by Guido van Rossum in 1989, and the first public release was released in 1991.

2. What are the features and advantages of Python?

- Easy to learn, simple and clear syntax, easy to read code
- Dynamically typed language, no need to declare variable types in advance
- Supports multiple programming paradigms, such as object-oriented, functional, imperative, etc.
- Open source and free, with an active community and a large number of excellent third-party libraries
- Interpreted language, no need to compile and run, high development and debugging efficiency
- High-level language, supports various system calls and memory management, and has good portability

3. What are the commonly used data types in Python?

Number, String, List, Tuple, Dictionary, Set, etc.

4. What are the variable naming rules in Python?

Variable names can only contain letters, numbers and underscores, cannot start with numbers, cannot use keywords and function names, and are strictly case-sensitive.

5. How to implement multi-line statements in Python?

You can use backslashes \ or parentheses () to implement multi-line statements. You can also use three single quotes ''' or three double quotes """ to assign a multiline string to a variable.

6. What are the annotation methods in Python?

Use # for single-line comments, and use three single quotes ''' or three double quotes """ for multi-line comments.

7. What is the basic syntax of if-else statement in Python?

```python
if condition:
     statements
elif condition:
     statements
else:
     statements
```

8. What is the syntax of for loop in Python?

```python
for variable in collection:
     statement
```

9. What is the syntax of while loop in Python?

```python
while conditional statement:
     Execute statement
```

10. What are the functions of break, continue, and pass statements in Python?

- break: jump out of the current loop
- continue: jump out of this cycle and continue to the next cycle
- pass: empty statement, generally used for placeholder

11. What is the basic syntax for defining functions in Python?

```python
def function name (parameter):
     function body
```

12. What is the parameter passing mechanism of functions in Python?

Python function parameters are passed by reference, that is, modifying parameters inside the function will affect external variables.

13. What are the mutable and immutable objects in Python?

- Mutable objects: lists, dictionaries, sets
- Immutable objects: numbers, strings, tuples

14. What are the common operations on lists in Python?

- Added: append, extend, insert
- Delete: remove, pop
- Modification: Assignment
- Search: index, count, in
- Sorting: sort, sorted, reverse

15. What is the slicing operation in Python?

```python
L[start:stop:step]
```

start is the starting subscript, stop is the ending subscript, and step is the step size, which can realize the slicing operation of the list.

16. What are the common operations on dictionaries in Python?

- Add/modify: d[key] = value
- Delete: del d[key] or d.pop(key)
- Search: d[key] or d.get(key)
- Traversal: for k in d or for k,v in d.items()

17. How to delete elements from a dictionary or set in Python?

- Delete dictionary elements: del d[key] or d.pop(key)
- Delete collection elements: s.remove(obj) or s.discard(obj)

18. What are decorators in Python? What are their functions?

A decorator is a function that is used to modify and extend the functions of other functions without directly modifying the source code of the decorated function. Common functions: logging, access control, caching, measuring time, etc.

Use the @ symbol to apply the decorator on the line before the function definition.

19. What are the three standard file objects in Python?

- stdin: standard input
- stdout: standard output
- stderr: standard error

20. What is the basic syntax for exception handling in Python?

```python
try:
     Possible error code
except:
     If an exception occurs, the code executed
```

If an exception occurs in the try code block, the except code block will be executed. If there is no exception, the except code block will not be executed.

21. What can Python’s list comprehensions be used for?

Lists can be quickly generated using more concise syntax, for example:

```python
[x**2 for x in range(10)]
```

22. Explain the function of pass statement in Python?

The pass statement is used as a placeholder and is equivalent to a no-op. Pass can be used when the syntax requires a statement but the logic does not require any operation to maintain the integrity of the program structure.

23. How to define constants in Python?

Constants can be represented by all-uppercase variable names, defined by importing the constants module, or simulated using the @lru_cache decorator of the functools module.

24. What is the slicing syntax in Python?

The slicing syntax in Python is as follows:

```python
Variable [start:stop:step]
```

start is the starting subscript, stop is the ending subscript, and step is the step size.

25. What are the LEGB rules in Python?

LEGB refers to the ordering rules of scopes in Python:

- L: Local namespace within the function
- E: Enclosing the namespace of nested functions
- G: Namespace of the Global module
- B: Namespace of Built-in Python built-in modules

Find variables in order LEGB.

26. What is the difference between is and == in Python?

is is used to compare the memory address of the object, == is used to compare the value of the object. When comparing basic data types, the two are equivalent. For container types such as list and dict, you should use == to determine the element value instead of is to determine the memory address.

27. How to copy an object in Python?

Objects can be copied using the copy or deepcopy functions of the copy module. An object can also be copied by calling the object's copy method.

28. What is the difference between eval and exec in Python?

- eval: Evaluate the string as a valid expression and return the result.
- exec: Execute a string as Python code.

29. What are the basic data structures of Python?

Numbers, strings, lists, tuples, dictionaries, sets.

30. Please explain what is GIL in Python?

GIL is Python's Global Interpreter Lock. It allows Python to have only one thread executing at the same time, preventing conflicts from modifying the same object at the same time. The GIL limits the parallelism of Python multithreaded programs.

31. How to implement deep copy and shallow copy in Python?

- Shallow copy: direct assignment, slice, dict.copy()
- Deep copy: copy.deepcopy(), uses recursion to copy the sub-objects inside the object

32. Python’s standard way to swap two variables?

Use tuple packing and unpacking to exchange variables:

```python
a, b = b, a
```

33. How to sort a dictionary based on its value in Python?

Use sorted and lambda functions:

```python
sorted(d.items(), key=lambda x:x[1])
```

34. How to get the type of object in Python?

Use the type() function or isinstance() function to determine the type. You can also use the __class__ attribute directly.

35. What is the difference between Python lists and arrays?

Arrays store a single type of data, and lists can store different types of data. Array elements are stored continuously in memory, and list elements are stored at any location in memory. Arrays are more suitable for numerical calculations, and lists are more suitable for storage and traversal.

36. What are the built-in data structures in Python?

List, tuple, dictionary, set.

37. Explain the concept of closures in Python.

Closure refers to a function that extends the scope and can access non-global variables that exist when the function is defined. To create a closure, at least the following conditions must be met:

- There must be an inline function
- Inline functions must reference variables in external functions
- External functions must return embedded functions

38. What is the principle of decorators in Python?

A decorator is essentially a function that allows other functions to add additional functions without making any code changes. The return value of the decorator is also a function object. Use the @ syntax to place the decorator before another function definition to implement the decoration function.

39. What is the role of generators in Python?

Generators can be used to create data streams that iterate on demand, saving a lot of memory space and implementing delayed calculations. In Python, functions containing the yield keyword are generators.

40. What is the difference between sets and lists in Python?

- Sets store non-repeating and unordered elements, while list elements are repeatable and ordered.
- Lists can contain elements of different types, and collections can only contain hashable objects.
- Lists support indexing and slicing operations, but collections do not.
- Sets support efficient member detection, lists require linear scanning

41. How to implement singleton pattern in Python?

Use decorators or metaclasses to implement the singleton pattern. The core idea is to control the instantiation operation and only allow one instance to be created.

```python
class Singleton(type):
     _instances = {}
     def __call__(cls, *args, **kwargs):
         if cls not in cls._instances:
             cls._instances[cls] = super(Singleton, cls).__call__(*args, **kwargs)
         return cls._instances[cls]
        
class MyClass(metaclass=Singleton):
     pass

```

42. Explain the difference between anti-iterators and generators in Python

Anti-iterators can traverse data elements infinitely without storing the entire data stream. The generator is also an iterator. The difference is that the generator requires the user to call next() to get the next data, which is more flexible.

43. What are the ways to format strings in Python?

- % operator formatting
- str.format() method formatting
- f-string formatting

44. What should you pay attention to when sharing data among multiple threads in Python?

Due to limitations of the GIL, multithreading in Python has no advantage for CPU-intensive tasks. Data shared between multiple threads needs to be protected by locks, otherwise there will be problems of data competition and deadlock.

45. Please briefly describe the role and implementation principle of the asyncio module in Python.

The asyncio module implements an asynchronous programming framework using coroutines. It is based on the event loop and can achieve non-blocking concurrency. The coroutine gives up control through await and executes tasks asynchronously without blocking the main thread.

46. What is a decorator in Python? What does it do?

A decorator is essentially a function that allows other functions to add additional functions without making any code changes. The return value of the decorator is also a function object. Use the @ syntax to place the decorator before another function definition to implement the decoration function.

Common uses include: inserting logs, performance testing, access control, caching, etc.

47. Talk about GIL locks in Python and its impact.

GIL is Python's global interpreter lock, which enables only one thread to execute at the same time, achieving interpreter-level synchronization.

The existence of GIL ensures thread safety, but also limits the concurrency of Python multi-threaded programs. Significant performance in CPU-intensive tasks.

48. How to merge multiple dictionaries in Python?

Use {} to create new dictionaries, and use the update method to update from other dictionaries:

```python
dict1 = {'a':1, 'b':2}
dict2 = {'b':3, 'c':4}
dict3 = {}
dict3.update(dict1)
dict3.update(dict2)
# {'a': 1, 'b': 3, 'c': 4}
```

You can also use the ** operator, and the subsequent dictionary will overwrite the keys of the same name in the previous dictionary:

```python
dict3 = {**dict1, **dict2}
```

49. Explain the slicing operation in Python.

Python slicing syntax:

```
Sequence [start:stop:step]
```

- start: starting subscript, default is 0
- stop: end subscript (not included)
- step: step size, default is 1

Slicing returns a new sequence composed of elements in the range, including the front and not the last.

50. What are the LEGB rules in Python?

LEGB is the scope ordering rule in Python:

- L - Local: Scope within the function
- E - Enclosing: Scope within the outer nested function
- G - Global: Global scope defined within the module
- B - Built-in: Scope in Python built-in modules

Variables are found in this order of rules.

51. What is the difference between list comprehensions and generator expressions?

List comprehensions will directly produce a list, while generator expressions will return a generator object, which requires iteration to obtain elements.

The generator does not need to generate and save all elements, so it saves memory space.

52. How to implement modifiable read-only attributes in Python?

This can be achieved through the property decorator:

```python
class Person:
   @property
   def name(self):
     return self._name
  
   @name.setter
   def name(self, value):
     self._name = value
```

name becomes a readable property, and setting name will call the setter method.

53. What are the scopes of variables in Python?

Variables in Python have 4 scopes:

- L - Local: variables defined within the function
- E - Enclosing: Variables defined within nested functions
- G - Global: variables defined in the module
- B - Built-in: Python built-in variable name

Find rule location variables in LEGB order.

54. How to dynamically get and set object properties in Python?

The built-in functions getattr() and setattr() can dynamically get and set object attributes.

getattr(obj, 'attr') is equivalent to obj.attr
setattr(obj, 'attr', value) is equivalent to obj.attr = value

55. Explain the metaclass in Python.

A metaclass is a class that creates class objects. The metaclass can intercept the creation of the class, modify the definition of the class, and return the modified class.

type is a common metaclass. Use metaclasses to implement functions such as singleton mode and log decorators.

The metaclass used is specified via __metaclass__ when defining a class.

56. Please explain MRO (Method Resolution Order) in Python.

MRO refers to the search order for methods in the case of multiple inheritance. It ensures that it starts from the subclass and searches in the direction of the parent class in order, following the C3 linearization algorithm.

The MRO of a class can be viewed through the __mro__ attribute.

57. Briefly describe the role of the Pickle module in Python.

Pickle implements object serialization, which can convert Python objects into byte streams, store them in files or databases, and then parse and restore the objects later. This achieves persistent storage of objects.

58. What is the function of decorator in Python?

A decorator is essentially a function that adds additional functionality to other functions or classes without directly modifying the definition of the function or class. The advantage of this is that the open and closed principle of the original function code is not modified.

Common uses include: logs, etc.

OK, continue to output:

59. How to implement the requirement that all objects be iterable in Python?

The custom object needs to implement the __iter__ method, which returns an iterator object; the iterator object needs to implement the __next__ method, which returns the next available element when called, and throws a StopIteration exception if there are no elements left. .

60. Explain the difference between is and == in Python

is is used to determine whether the identities (memory addresses) of two objects point to the same object. == is used to determine whether the values of two objects are equal.

When comparing basic data types, is and == have the same effect; for container types such as list and dict, == should be used to determine the element value instead of is to determine the memory address.

61. What are the functions of *args and **kwargs in Python?

*args is used to assemble an indefinite number of positional parameters into a tuple and pass it to the function.

**kwargs is used to assemble an indefinite number of keyword parameters into a dict and pass it to the function.

62. Talk about mutable types and immutable types in Python?

Immutable types: numeric types, strings, tuples. Once an object is created, its own properties cannot be modified.

Variable types: list, dictionary. After an object is created, the values of its properties can still be modified.

63. How to modify global variables inside a function in Python?

Just use the global keyword to declare variables:

```python
count = 0
def add():
     global count
     count += 1
```

64. The problem of variable scope in Python functions, how to access variables in different scopes?

Variables in Python are searched according to LEGB rules:

- Local: function internal scope
- Enclosing: Scope within the outer nested function
- Global: module global scope
- Built-in: System built-in scope

Use the global keyword declaration to modify global variables, and use the nonlocal declaration to access variables in nested function scopes.

65. What is object-oriented programming? Please briefly describe the relationship between classes and objects in Python.

The idea of object-oriented programming is to use classes and objects to organize code and encapsulate data and methods of operating data.

In Python, classes provide a blueprint for objects, and an object is a concrete existence created based on a class. Objects contain properties and methods for representing data and manipulating data.

66. How to set access restrictions on object properties in Python?

Properties of Python objects are public by default. To set a private attribute, you need to add two underscores __ before the attribute name, so that the attribute cannot be accessed externally.

Private properties can be accessed and modified through object methods.

67. Explain the difference between new-style classes and old-style classes in Python

In Python2:
- New style class: class that inherits object
  -Old-style classes: classes that do not inherit object

New-style classes have many features such as attribute descriptors, __slots__, metaclasses, etc. It is recommended to use new style classes.

In Python 3, all classes are required to inherit object and are all new-style classes.

68. What are the differences between class methods, static methods and instance methods in Python?

- Instance method: the first parameter is self, which represents the instance itself
- Class method: The first parameter is cls, which represents the class itself
- Static method: ordinary function, no default first parameter

69. What is the reflection mechanism in Python? How to implement it?

Reflection is to operate object-related properties in the form of strings.

Reflection is mainly implemented through getattr(), setattr(), hasattr() and other methods.

70. How does Python implement overloaded instance methods?

Overloading is achieved by defining method names like __method class. By passing in self and method names, you can call overloaded instance methods.

71. How to dynamically create classes in Python?

You can use the type function to dynamically create classes. The basic syntax is as follows:

```python
MyClass = type('MyClass', bases class tuple, attribute dictionary)
```

You can also use metaclass to achieve more flexible class creation.

72. What is a metaclass in Python?

Metaclasses are classes used to create classes. The metaclass can intercept the creation of a class, modify the definition of the class, and return the modified definition of the class.

Use the metaclass parameter to define a class and its metaclass:

```python
class MyClass(metaclass=MetaClass):
   ...
```

73. What is a descriptor in Python? How to implement a descriptor?

A descriptor is an object property with binding behavior. When accessing the property, the descriptor method will be called.

A descriptor can be implemented by defining __get__(), __set__(), and __delete__() methods.

74. What is the function of @property decorator?

The @property decorator converts class methods into readable properties, which can be accessed syntactically as properties, avoiding direct exposure of method calls.

When setting the value, you need to use the setter method.

75. What is aspect-oriented programming AOP and its implementation in Python?

AOP realizes the isolation of program logic and inserts new functions into the program without modifying the source code through decorators, metaclasses, etc.

The decorator function in Python is a simple AOP implementation.

76. Talk about the implementation of singleton pattern in Python?

Use the __new__ method to implement the singleton pattern. If it is judged that the instance exists, it will be returned directly. Otherwise, a new instance will be created and stored in the instance dictionary.
At the same time, the __init__ method should be defined to prevent new instances from being created.

You can also use decorators to implement singletons.

77. How to implement derivation of lists, dictionaries, and sets in Python?

```python
[ expr for value in collection ]
{ key_expr:value_expr for value in collection}
{ expr for value in collection }
```

Use concise syntax to filter/convert elements based on sets to generate lists, dictionaries, and sets.

78. Briefly describe list comprehension in Python?

List comprehensions provide a simpler and more efficient way to create lists. Basic syntax:

```python
[ expr for value in collection ]
```

Generating a new list based on the collection according to certain rules can avoid the tediousness of the for loop.

79. What is the parameter passing mechanism of Python functions?

Python function parameters pass objects by reference. When an immutable object is passed in, the external object cannot be changed inside the function; but changes to the mutable object within the function will affect the external object.

80. What is the difference between is and == in Python?

is is used to compare the identity of an object, and == is used to compare whether the values of two objects are equal.

is determines whether the object is the same, and == determines whether the values are equal. For immutable types, the two are usually equivalent.

81. Please briefly describe the GIL in Python.

GIL is Python's global interpreter lock, which implements interpreter-level thread synchronization. GIL ensures that only one thread is executed at the same time, so multi-threading cannot take advantage of multi-core CPUs in Python.

The existence of GIL ensures thread safety, but also limits the concurrency of Python's multi-threading.

82. What is closure? Please briefly describe the function and advantages of closure.

A closure is a whole combination of a function and the environmental state (Lexical scope) when the function is declared. Closures have access to non-global variables that were available when the function was defined.

The advantage is to avoid global variable pollution, but the disadvantage is that variables are always in memory and consume memory.

83. Briefly describe the difference between method overloading and rewriting in object-oriented

Method overloading refers to methods in the same class that define different parameters with the same name. Python distinguishes between different methods through parameters at run time.

Method rewriting means that the subclass implements the same method as the parent class method, and PYTHON will call the rewritten method of the subclass.

84. What is the function of Python’s decorator?

A decorator is essentially a callable object that adds new functionality to a function without changing the original function code. Use the @ syntax to place the decorator above the function definition.

Common uses include: inserting logs, performance testing, access control, caching, etc.

85. What are the common methods of Python list (list)?

- Added: append(), extend(), insert()
- Delete: remove(), pop(), clear()
- Sorting: sort(), reverse()
- Access: __getitem__(), __setitem__(), __len__()
- Search: index(), count(), in

86. How to implement deep and shallow copy in Python?

Shallow copy: Slice operations on container objects, factory function copy.copy(), etc.

Deep copy: Use copy.deepcopy() to recursively copy the internal elements of the object

87. What is the slots mechanism in Python? What are the usage scenarios?

Slots is a class variable, which limits objects to only have attributes defined in slots, and dynamic addition is prohibited.

The advantage is to reduce memory overhead and improve access speed; the disadvantage is to limit flexibility.

It is suitable for scenarios with fixed class attributes and a large number of objects.

88. Briefly describe the implementation mechanism of overloaded instance methods in Python

Overloading is achieved by defining a special __method name format method on the instance. When calling an instance method, this special method will be searched first and called.

By passing in self and method name parameters, any overloaded method can be called.

89. What is the function of @property?

Convert methods into class attribute calls to make syntax more elegant.

Use @property to decorate the getter method and @methodname.setter to decorate the setter method.

90. What is the difference between list and tuple?

- Lists are mutable, tuples are immutable
- Lists are defined with square brackets, tuples are defined with parentheses
- List comprehensions generate new lists, and tuples have no comprehensions.
- The list can be modified using addition and deletion operations, and the tuple is immutable

91. Please briefly describe the main function of the sys module in Python

The sys module is mainly used to provide system environment information and functions for interacting with the Python interpreter.

The main function:

- Get object references: argv, path, modules, etc.
- Get restriction information: maxsize, maxunicode, etc.
- Perform interactive operations with the parser: exit(), getrefcount(), etc.

92. How to implement counter (count) in Python?

The counter function can be implemented using the count() function of the itertools module. Each call will return an incrementing integer:

```python
import itertools
counter = itertools.count()
print(next(counter), next(counter)) # 0 1
```

93. What are the list sorting methods in Python? What are their respective time and space complexities?

- Built-in function sorted():O(nlogn)
- list.sort():O(nlogn)
- sorted(items, key=...): O(nlogn)
- Manual sorting algorithm: selection, bubble O(n^2)

94. Explain the use of closures that return local variables in Python

A closure that returns a local variable can maintain the state of the local variable during the function call, so that this state can be maintained for the duration of the closure.

Application scenarios such as: counters, status markers, etc. that need to save information between function calls.

95. Please briefly describe some magic methods in Python

- \_\_init\_\_: object initialization calling method
- \_\_str\_\_: Convert to string method
- \_\_len\_\_: object length method
- \_\_call\_\_: Object call method
- \_\_getitem\_\_: Implement sequence index access
- \_\_repr\_\_: Generate a representation that can be read by the parser

and other magic methods that implement the built-in language functionality of the object.

96. Briefly describe the differences between read, readline and readlines

- read(): Read the entire file content and return a string.
- readline(): Read the next line and return a string.
- readlines(): Read the entire file and return a list of line strings.

97. Talk about the method of exception handling in Python

Use try-except code blocks for exception handling:

```python
try:
   Code that may cause exceptions
exceptExceptionType:
   Exception occurs when executing code
```

The try block detects exceptions and jumps to the corresponding except block for processing if an exception occurs.

98. Is the mechanism for passing function parameters in Python by value or by reference?

Python parameters are passed into functions by reference.

Modifications to immutable objects within a function do not affect external variables, that is, value transfer; modifications to mutable objects affect external variables, that is, reference transfer.

99. What are dictionary comprehensions in Python?

Dictionary comprehensions create new dictionaries from other dictionaries or sequences. The syntax format is as follows:

```python
{key_expr: value_expr for value in collection}
```

Filter and convert elements according to collection to generate dictionary type.

100. Briefly describe the usage of unittest framework in Python

Unittest implements the functions of test case collection, running test cases, and verification results. It mainly involves four components:

- TestCase: Define the test class, including each test method
- TestSuite: test collection
- TestRunner: Run test cases
- TestLoader: Load TestCase into TestSuite

Write TestCase test class and run test cases through TextTestRunner.