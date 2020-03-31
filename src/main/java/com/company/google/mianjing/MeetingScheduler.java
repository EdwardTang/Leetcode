package com.company.google.mianjing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MeetingScheduler {
    static class Interval {
     int start;
      int end;
      Interval() { start = 0; end = 0; }
      Interval(int s, int e) { start = s; end = e; }
     }

     static class Point{
       int time; // the time of this point on timeline
       int type; // 1 reprents start of interval, -1 represents end of interval.
       Interval interval;
       Point(int time, int type, Interval interval){
         this.time = time;
         this.type = type;
         this.interval = interval;
       }
     }

  public static boolean canScheduleOneRoom(Interval[] intervals, Interval newInterval) {
    if(newInterval.end < intervals[0].start) {
      return true;
    }
    if(newInterval.start > intervals[intervals.length - 1].end) {
      return true;
    }
    for(int i = 1; i < intervals.length; i++) {
      // case 1 : new interval end < first start
      // case 2: new interval start > last end
      // case 3: previous interval.end < new interval start < next interval.start
      if(intervals[i - 1].end < newInterval.start && newInterval.end < intervals[i].start) {
        return true;
      }
    }

    return false;
  }

  public List<Point> getTimeline(Interval[] intervals) {
    List<Point> timeLine = new ArrayList<>();
    for(Interval interval : intervals){
      timeLine.add(new Point(interval.start, 1, interval));
      timeLine.add(new Point(interval.end, -1, interval));
    }
    Collections.sort(timeLine, (a, b)->{
      if(a.time == b.time){
        return a.type < b.type ? -1 : 1;
      }else{
        return a.time < b.time ? -1 : 1;
      }
    });
    return timeLine;
  }

//  public static boolean canScheduleStream(List<Point> timeline, Interval newInterval) {
//    Point start = new Point(newInterval.start, 1);
//    Point end = new Point(newInterval.end, -1);
//
//    if(end.time < timeline.get(0).time || start.time > timeline.get(timeline.size() - 1).time) {
//      return true;
//    }
//
//    int left = timeline.get(0).time
//
//  }
}

