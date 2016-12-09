#Hasher

##About

I made this app because I tried to download two separate apps from the Play Store to validate a system image's checksum. Neither worked, so I made my own!

##Installation

Pre-compiled apks are under `Releases`. 
Right now it's targeted for `API 23` and above, but hopefully that will change in the future.

##Usage

Open any file and select the hashing algorithm you would like to use. Click `HASH` and the file's hash will be printed.
If you have a reference hash, copy it to your clipboard and click on `Tap to paste...` to compare the two hashes.

Currently has support for
* MD5
* SHA1
* SHA256
* SHA384
* SHA512
* more coming soon...

##Future

I plan on adding support for:

* More hashing algorithms
* A file name label
* Threading to prevent hangs when hashing large files
* A prettier UI, maybe better animations
* An actual app icon
* Bugfixes as they are discovered

##Screenshots

![Screenshot](https://i.imgur.com/yMEN3jq.png)

##License

The MIT License (MIT)

Copyright (c) 2015 Yuriy Chuhaienko

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
