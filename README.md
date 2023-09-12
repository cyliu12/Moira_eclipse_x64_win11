# Moira_eclipse_x64_win11
Moira 七政四餘星盤。為解決在Windows 11下按鈕顯示異常問題，使用最新的SWT跟jface，但也因此需要java 17，Runtime用openJDK解決。csharp.Moira在這裡沒有用處，因為IKVM只支援到SE 8。

使用說明。在Eclipse開一個空白workspace，裡使用Import Projects from Git把這裡的project都裝進去，加好之後，應該就能執行了。
進入點在Moira\src\org.athomeprojects.moira.Moira
也有可能會遇到一些問題，可以參考以下幾個link
[API baseline not set...]
https://stackoverflow.com/questions/29473967/eclipse-gives-an-api-baseline-has-not-been-set-for-the-current-work-space-er

如果要Export並打包成執行檔，會需要Eclipse Temurin的openJDK_JRE，要17版的，link如下
https://adoptium.net/temurin/releases/

Export成JAR，使用上面的jre用Launch4j打包，我試過可以執行
