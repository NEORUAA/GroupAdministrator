package top.mc114.groupadmin;

import kotlinx.coroutines.Dispatchers;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.message.GroupMessage;
import net.mamoe.mirai.message.data.At;
import net.mamoe.mirai.message.data.Image;
import net.mamoe.mirai.message.data.MessageUtils;

import javax.naming.ldap.Control;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.function.Consumer;

public class GroupMessageListener implements Consumer <GroupMessage> {
    @Override
    public void accept(GroupMessage event) {
        /*if(event.getMessage().toString().contains("����")) {
            try {
                Image img = event.getGroup().uploadImage(new URL(event.getSender().getAvatarUrl()));
                event.getGroup().sendMessage(MessageUtils.newChain(img)
                .plus("����һ�������")
                .plus(new At(event.getSender())));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }*/
        //System.out.println(event.getGroup().getBotPermission().getLevel());//1=Administrator,0=Member
        if(event.getGroup().getBotPermission().getLevel()==0||event.getSender().getPermission().getLevel()>0) {
            //System.out.println("Permission denied!");
            return;
        }
        String msg = event.getMessage().toString();
        if(msg.toLowerCase().contains("ddos")||msg.contains("ɵ��")||msg.contains("��ǽ")||
                msg.toLowerCase().contains("vpn")||msg.contains("ϰ��ƽ")||msg.toLowerCase().contains("ssr")||
                msg.toLowerCase().contains("v2ray")||msg.contains("�繤")||msg.contains("����")||
                msg.contains("����")||msg.contains("��̱")||msg.contains("����")) {
            event.getBot().recall(event.getMessage());
            int time = 600;
            event.getSender().muteAsync(time);
            event.getGroup().sendMessage(MessageUtils.newChain(new At(event.getSender()))
                    .plus("��ʹ����Υ���ʣ�����˱�����"+time+"�룬��ע���Լ������У�")
            );
         }
    }
}
