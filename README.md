# Jacoco XML Parser

Reads Jacoco XML report files, parses them and prints pretty terminal reports

```
usage: jacocoxmlparser-1.0.0.jar [--color] -g | -p -i <arg>
version: 1.0.0
Parse Jacoco XML files and print reports.

    --color           If set, colorizes the results based on the
                      percentage of code coverage. Does not work on
                      Windows
 -g,--global-stats    If set, print global coverage report
 -i,--input <arg>     The target XML file
 -p,--package-stats   If set, print package coverage report

Sample usage:

 * jacocoxmlparser-1.0.0.jar --input ~/tmp/file.xml --global-stats

```

## Sample output

##### Global stats (`-g`):
![GitHub Logo](/readme_assets/global_report.png)

With color!
![GitHub Logo](/readme_assets/global_report_color.png)

##### Package stats (`-p`):
![GitHub Logo](/readme_assets/package_report.png)

With color!
![GitHub Logo](/readme_assets/package_report_color.png)

## Build Instructions

Linux/Mac: `mvn clean package && chmod +x target/jacocoxmlparser-X.X.jar`

## Versions

* 1.0.0: First release.

## Links

* Github: [https://github.com/alt236/jacocoxmlparser]()
* Bug reports: [https://github.com/alt236/jacocoxmlparser/issues]()
* Releases: [https://github.com/alt236/jacocoxmlparser/releases]()

## Credits

Author: [Alexandros Schillings](https://github.com/alt236).

The XML parser a slightly modified version of the one in [apk-extractor](https://code.google.com/archive/p/apk-extractor) by Prasanta Paul.

The code in this project is licensed under the [Apache Software License 2.0](LICENSE).

## License
Copyright (c) 2019-present, Alexandros Schillings.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
