/*
 * Decompiled with CFR 0.145.
 */
package me.mysticoverlord.mysticoverbot.objects;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;

public class FormatUtil {
    public static String formatTime(long duration)
    {
        if(duration == Long.MAX_VALUE)
            return "LIVE";
        long seconds = Math.round(duration/1000.0);
        long hours = seconds/(60*60);
        seconds %= 60*60;
        long minutes = seconds/60;
        seconds %= 60;
        return (hours>0 ? hours+":" : "") + (minutes<10 ? "0"+minutes : minutes) + ":" + (seconds<10 ? "0"+seconds : seconds);
    }
    
    public static String formatDayTime(long seconds)
    {
        long days = seconds/(60*60*24);
        seconds %= 60*60*24;
        long hours = seconds/(60*60);
        seconds %= 60*60;
        long minutes = seconds/60;
        seconds %= 60;
        return (days>0 ? days+":" : "") + (hours>0 || days>0 ? (hours<10 ? "0" + hours+":" : hours + ":") : "") + (minutes>0 || hours>0 || days>0 ? (minutes<10 ? "0"+minutes : minutes) + ":" : "") + (seconds<10 ? "0"+seconds : seconds);
    }
    
    public static long toMilliseconds(int hour, int minute, int second) {
    	long seconds = second + (minute * 60) + (hour * 60 * 60);
    	return seconds * 1000;
    }


    public static String progressBar(double percent) {
        String str = "";
        for (int i = 0; i < 12; ++i) {
            str = i == (int)(percent * 12.0) ? String.valueOf(str) + "\ud83d\udd18" : String.valueOf(str) + "\u25ac";
        }
        return str;
    }

    public static String volumeIcon(int volume) {
        if (volume == 0) {
            return "\ud83d\udd07";
        }
        if (volume < 30) {
            return "\ud83d\udd08";
        }
        if (volume < 70) {
            return "\ud83d\udd09";
        }
        return "\ud83d\udd0a";
    }

    public static String listOfTChannels(List<TextChannel> list, String query) {
        String out = " Multiple text channels found matching \"" + query + "\":";
        for (int i = 0; i < 6 && i < list.size(); ++i) {
            out = String.valueOf(out) + "\n - " + list.get(i).getName() + " (<#" + list.get(i).getId() + ">)";
        }
        if (list.size() > 6) {
            out = String.valueOf(out) + "\n**And " + (list.size() - 6) + " more...**";
        }
        return out;
    }

    public static String listOfVChannels(List<VoiceChannel> list, String query) {
        String out = " Multiple voice channels found matching \"" + query + "\":";
        for (int i = 0; i < 6 && i < list.size(); ++i) {
            out = String.valueOf(out) + "\n - " + list.get(i).getName() + " (ID:" + list.get(i).getId() + ")";
        }
        if (list.size() > 6) {
            out = String.valueOf(out) + "\n**And " + (list.size() - 6) + " more...**";
        }
        return out;
    }

    public static String listOfRoles(List<Role> list, String query) {
        String out = " Multiple text channels found matching \"" + query + "\":";
        for (int i = 0; i < 6 && i < list.size(); ++i) {
            out = String.valueOf(out) + "\n - " + list.get(i).getName() + " (ID:" + list.get(i).getId() + ")";
        }
        if (list.size() > 6) {
            out = String.valueOf(out) + "\n**And " + (list.size() - 6) + " more...**";
        }
        return out;
    }

    public static String filter(String input) {
        return input.replace("@everyone", "@\u0435veryone").replace("@here", "@h\u0435re").trim();
    }


    
    public static String dateCalculator(String days, String hours, String minutes) {
    	String pattern = "yyyy-MM-dd-HH-mm";
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    	String date = simpleDateFormat.format(new Date());
    	
    		Integer day = Integer.parseInt(date.substring(8, 10));  
    		Integer month = Integer.parseInt(date.substring(5, 7));
    		Integer year = Integer.parseInt(date.substring(0, 4));
    		Integer minute = Integer.parseInt(date.substring(14));
    		Integer hour = Integer.parseInt(date.substring(11, 13));
    		
    		minute += Integer.parseInt(minutes);
    		day += Integer.parseInt(days);
    		hour += Integer.parseInt(hours);
    		
    		if (minute >= 60) {
    			hour += 1 * (minute / 60);
    			minute -= 60 * (minute / 60);
    		}
    		
    		if (hour >= 24) {
    			day += 1 * (hour / 24);
    			hour += 24 * (hour / 24);
    		}
    		
    	while (day > 31) {
    		if (day > 28) {
    			if (month == 2) {
    				month += 1;
    				day -= 28;
    			}
    		}
    		
    		if (day > 30) {
    			if (month == 4 || month == 6 || month == 9 || month == 11) {
    				day -= 30;
    				month += 1;
    			}
    		}
    		
    		if (day > 31) {
    			day -= 31;
    			month +=1;
    		}
    	}
    		
    		if (month > 12) {
    			year += 1 * (month / 12);
    			month -= 12 * (month / 12);
    		}
    		
    		String y = String.valueOf(year);
    		String m;
    		String d;
    		String mm;
    		String h;
    		if (minute < 10) {
    			mm = "0" + String.valueOf(minute);
    		} else {
    			mm = String.valueOf(minute);
    		}
    		
    		if (hour < 10) {
    			h = "0" + String.valueOf(hour);
    		} else {
    			h = String.valueOf(hour);
    		}
    		
    		if (day < 10) {
    			d = "0" + String.valueOf(day);
    		} else {
    			d = String.valueOf(day);
    		}
    		
    		if (month < 10) {
    			m = "0" + String.valueOf(month);
    		} else {
    			m = String.valueOf(month);
    		}
    		
    		return y + "-" + m + "-" + d + "-" + h + "-" + mm;
    	}
    	
    public static String embedLink(String link, String text) {
        return "[" + text + "](" + link +  ")";
    }
}

