package me.mysticoverlord.mysticoverbot.objects;

import net.dv8tion.jda.api.entities.Member;

public class MusicUtil {

    public static Boolean isDJ(Member member) {
    for (int x = 0; x < member.getRoles().size(); x++) {
    	if (member.getRoles().get(x).getName().equalsIgnoreCase("DJ")) {
    		return true;
    	}
    }
    	return false;
    }
}
