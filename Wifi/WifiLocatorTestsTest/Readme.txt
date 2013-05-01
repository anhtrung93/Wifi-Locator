How to run Tests:

1. With the Junit Tests: WifiSignatureTest, FingerprintTest, ConstantTest, RequestTest

Find Running configurations -> java application
In the new configuration's Classpath tab, find "Android Library" under Bootstrap Entries and remove it.
Still in the Classpath tab, select Bootstrap Entries and click the Advanced button.
Choose Add Library and click OK.
Select JRE System Library and click Next.
Select Workspace Default JRE and click Finish
http://stackoverflow.com/questions/9455236/eclipse-error-noclassdeffounderror-java-lang-ref-finalreference

2. With the Android Tests: WifiLocatorTest
Connect an android device into the computer or create an AVD simulation and run Eclipse ADT