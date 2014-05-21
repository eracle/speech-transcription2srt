Speech-Transcription to Srt
=======
 
Introduction
-----------
Actual speech transcription systems can recognize spoken words in audio files. Togheter with them are also stored timing information like start time and end time of every single words.
An interesting application of speech transcription systems are the automating generation of human readable subtitles.
The subtitles must follow some simple guidelines in order to make them easily human-readable.

Input Format 
-----------
We can think the input as a sequence of intervals, every interval is the time since the end_time and the start_time of every input word.
So we have as input a speech transcription in the following format:

word1,start_time,end_time
word2,start_time,end_time
...

And we want to generate an srt file with those words and appropriate timings.

Output Format
----------
From Wikipedia [1]:
** SubRip (SubRip Text) files are named with the extension .srt, and contain formatted lines of plain text in groups separated by a blank line. Subtitles are numbered sequentially, starting at 1. The timecode format used is hours:minutes:seconds,milliseconds with time units fixed to two zero-padded digits and fractions fixed to three zero-padded digits (00:00:00,000). **

Another way of thinking to the srt file is as a sequence of time intervals, with every one of them containing a set of consecutive input words.

For Example:

1
00:00:10,500 --> 00:00:13,000
Elephant's Dream

2
00:00:15,000 --> 00:00:18,000
At the left we can see...


Problem Description
-----------
The problem is to group consecutive input words in order to be as best as possible human-readable.

So we note some tricks of good subtitles.

1. Is better to prefer long subtitles instead of smaller and frequent; the activity of reading is fastest than the activity of speaking, so the reader usually read the entire subtitle and then changes the look to the image; we consider changing the look an activity subject to stress, so we prefer to minimize the number of times it happends.

2. There are silences inside the words of an srt sentence, a sentence must be splitted into two sub-sentences if the silence is too long.

Proposed Approach
-----------
We start analyzing the entire sequence of word by identify the silence intervals. So we split the sequence in two parts, the part which lies before the maximum silence interval and the part which lies after.
In other words we perform a Divide et Impera algorithm. 
The algorithm continue to split the sequence into sub-sequences until the objective sub-sequence contains more than a predefined number (threshold) of characters or inside that sequence there is an enough large time interval.



Bibliography
-----------
[1]	http://en.wikipedia.org/wiki/.srt#SubRip_text_file_format
