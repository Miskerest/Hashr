# About

I made this app because I tried to download two separate apps from the Play Store to validate a system image's checksum. Neither worked, so I made my own!

It's designed to be simple, fast, and easy to use. This is my first published Android application, so feedback is appreciated!

# Installation

Requires Android 4.4+

### Sources

* [Google Play](https://play.google.com/store/apps/details?id=com.misker.mike.hasher)
* Pre-compiled apks are under [Releases](https://github.com/Miskerest/Hashr/releases)
* [Amazon App Store](http://a.co/dk4aA1O)

### Permissions

* read the contents of your USB storage
* receive data from Internet
* view network connections
* full network access
* prevent device from sleeping

# Usage
1. Select whether you would like to hash a file or raw text.
2. Open any file or enter text in the text field.
3. Select the hashing algorithm you would like to use. Click `HASH` and the file's hash will be calculated and printed.
4. (optional) If you have a reference hash, copy it to your clipboard and click on `Tap to paste...` to compare the two hashes.

### Currently has support for
* MD5
* SHA1
* SHA256
* SHA384
* SHA512
* CRC32
* Adler32

# Future Plans

* Showing filename for selected file
* A prettier UI, better animations
* Add testing code (In progress)
* Translations for select locales

Bugfixes are being fixed as they are discovered

# Screenshots
<img src="https://lh3.googleusercontent.com/CqD_ufFeLSInDyIzIrKfbRXHDDuAkeFv5I21zGiMCqFi1m8NjqJYcKdACMQDbKUZ5ow=h900"  width="420" height="900"><img src="https://lh3.googleusercontent.com/wCIF0JWT7eTv3yUPMQ8xfMGQdWdyl0ugq5O3fj8pi8Thxn52l_abSE9yXzcg8YWOQGXF=h900-rw"  width="420" height="900">

## License

The MIT License (MIT)

Copyright (c) 2016 Mike Bailey

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
