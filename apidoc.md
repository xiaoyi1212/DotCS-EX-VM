# DotCS EX编程语言| Development API DOC

### 本地函数 | NativeFunction

<hr>

#### lib : SYSTEM
|函数名|参数|返回值|参数说明|说明|
|:---|---|:---|---|:---|
|printf|<-string->|void|要输出的信息|向终端（控制台）输出一段信息|
|freeVM|void|void|无|启动虚拟机线程回收机制|
|input|void|string|无|使当前线程进入Wait状态，并获取控制台的输入|
|thread|<-string-><-string->|void|<-创建的线程名-><-函数名->|创建一条线程,如果线程名为NULL，线程管理器会自动分配一个名称|
|sleep|<-name-><-int->|void|<-线程名-><-休眠的时间->|使当前线程进入Wait状态，并经过指定时间后重新启动|
|shutdown|<-int->|void|返回的值|结束当前程序|

<hr>

#### lib : FileManage
|函数名|参数|返回值|参数说明|说明|
|:---|---|:---|---|:---|
|write|<-file-><-string->|void|<-文件实例-><-写入信息->|在指定文件末尾追加信息|
|writeOverride|<-file-><-string->|void|<-文件实例-><-写入信息->|覆盖文件原有内容重新写入|
|read|<-file->|string|文件实例|读取指定文件|
|getFile|<-string->|file|文件名称|获取指定文件|
|isEmpty|<-file->|boolean|文件实例|判断文件是否存在|
|getPath|<-file->|string|文件实例|获取文件绝对路径|

<hr>

### 异常错误 | ExceptionError

> 注意：所有Error类型异常无法进行处理，如果抛出解释器可能直接停止运行

|异常名|类型|抛出原因|
|:---|---|:---|
|RuntimeError|Error|解释器运行时发生错误|
|VMLoaderError|Error|解释器初始化时发生错误|
|OutOfMemoryError|Error|解释器内存溢出时发生错误|
|JVMError|Error|Java版解释器发生错误|
|PVMError|Error|Python版解释器发生错误|
|FNFException|Exception|文件无法找到时发生异常|
|IOException|Exception|IO操作时发生异常|
|UException|Exception|未知异常或发生非解释器本地异常时抛出|
|NPException|Exception|获取到空指针时发生异常|
|JVMException|Exception|Java版解释器内部发生异常|
|PVMException|Exception|Python版解释器内部发生异常|
|AIOOBException|Exception|列表下标越界发生异常|

> 一个标准的异常信息抛出时应该输出此类型信息\
> 注意： Funtion stack info 不会列出所有本地或外部库函数，只会列出已加载的脚本函数
> > Exception throw [ExceptionName] : [Message]\
> > Function stack info:\
> > <-List all loaded function->
