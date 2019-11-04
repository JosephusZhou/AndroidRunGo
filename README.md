# AndroidRunGo

将 Golang 程序运行在 Android 上面（假设读者有一定的 Android 基础和 Linux 基础）。

### 编写 Go 程序

首先安装 Go 编译运行环境。

接着，开始写一个简单的 Go 程序。代码如下：

```go
package main

import (
	"fmt"
	"net/http"
)

func main() {
	http.HandleFunc("/", getIndex)
	http.HandleFunc("/hello", getHello)
	http.ListenAndServe(":8888", nil)
}

func getIndex(w http.ResponseWriter, r *http.Request) {
	fmt.Fprintf(w, "This is a golang server!")
}

func getHello(w http.ResponseWriter, r *http.Request) {
	fmt.Fprintf(w, "hello Golang!")
}
```

将文件命名为 `server.go`。运行以下编译命令，将 Go 程序编译成对应架构平台的二进制文件。这里以 linux 的 arm64 为例，最后得到 `server.so` 文件。

```shell
GOOS=linux GOARCH=arm64 go build -o server.so
```

### 编写 Android 程序

首先创建一个 Android 程序。

接着，由于使用到的 Go 程序涉及到网络服务，因此需要在声明网络权限。

**注意，如果打包的 Go 程序需要用到对应的权限，一定得在打包的 Android 程序里面配置和申请相应的权限。**

```xml
<uses-permission android:name="android.permission.INTERNET" />
```

将编译好的 `server.so` 文件放置在 jniLibs 中，这里为 `app/src/main/jniLibs`，你也可以自行指定 jniLibs 位置。

以下是运行 Go 程序的关键代码。

```kotlin
Runtime.getRuntime().exec(applicationInfo.nativeLibraryDir + "/server.so")
```

如果需要获取运行状态，可以声明一个 `Process` 对象，并获取运行状态和输入输出流，此处不做赘述。

运行Android 程序成功后，在运行设备上就可以访问 [http://127.0.0.1:8888/](http://127.0.0.1:8888/) 和 [http://127.0.0.1:8888/hello](http://127.0.0.1:8888/hello) 了。当然也可以在局域网的其他设备通过运行设备的 ip 地址访问到程序。

### 后记

一次小小的尝试，有了更多的可能性。

Go 不会用来写 UI，毕竟其他轮子很成熟了，没必要恶心自己，但是却可以用来写很多底层服务，比如核心加密模块等，不考虑性能的情况下(性能未知)用来取代 C++开发原生 Library 效率还是蛮不错的。

~~用 Go 写成的 Clash 可以跑在 Android 上了~~