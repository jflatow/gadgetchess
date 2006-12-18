#! /usr/bin/perl

use strict;

my $gadget_url = "http://flatow.googlepages.com/";

print 
q{<?xml version="1.0" encoding="UTF-8" ?> 
<Module>
 <ModulePrefs title="Asynchronous Chess 2.0" height="375" author="Jared Flatow" author_email="jflatow@gmail.com" author_affiliation="Rice University" screenshot="http://jflatow.googlepages.com/screenshot.png" thumbnail="http://jflatow.googlepages.com/thumbnail.png" description="Multiplayer game/gadget written entirely in javascript (so you don't need to download anything). Load up any game listed on the group and make the next move. Coordinate games with coworkers and play throughout the day/week. This is NOT fast-paced chess, and sometimes you will have to wait for Google groups to update searches before moves are posted. This is also not robust to malicious users, so play nice. Please report bugs in gameplay. Enter moves like 'e2e4' for normal moves, or '0-0'/'0-0-0' for kingside/queenside castle and 'e8Q' for pawn promotion to queen. Stores games on google group: gchess. Comments on games are welcome in the group inline with the games, though make sure you put them on separate lines.">
   <Require feature="setprefs" />
 </ModulePrefs>
 <UserPref name="lastGame" default_value="" datatype="hidden"/>
 <Content type="html">
 <![CDATA[
};
 
while (<>) {
    s/\"(\w+\.css)\"/\"$gadget_url$1\"/;
    print unless m/<\/?((html)|(head)|(body)).*>/i;
}
print 
q{]]>
 </Content> 
</Module>
};
