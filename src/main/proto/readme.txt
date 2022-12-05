message Video
비디오를 분할 전송하기 위한 자료구조. 이 데이터는 stream으로 보내지며,
(즉, 여러개의 데이터로 쪼개 보내짐)
맨 처음에는 Video에 대한 메타데이터(비디오 이름, 파일 형식 등)이 보내지고,
그 다음에는 Video가 바이트 단위로 쪼개서 보내진다.
C++상에서 쪼개져서 도착한 비디오를 합치면 비디오가 나온다.
(그냥 concatenate 하면 된다. bytesA + byteB + ...)



service VideoDisassembly
비디오를 프레임에 변화에 따른 박스들로 나누는 과정


service VideoAssembly
머신러닝이 끝난 박스들을 비디오로 합치는 과정

