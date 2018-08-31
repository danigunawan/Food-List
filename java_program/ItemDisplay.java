import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import java.awt.BorderLayout;
import java.util.Map;
import java.util.Iterator;

public class ItemDisplay extends JPanel {
  public ItemDisplay(Map items) {
    JTextArea contents = new JTextArea();
    contents.setEditable(false);
    contents.setLineWrap(true);
    this.setLayout(new BorderLayout());
    this.setBorder(new EtchedBorder());

    Iterator it = items.entrySet().iterator();
    while(it.hasNext()) {
      Map.Entry pair = (Map.Entry)it.next();
      contents.append(pair.getKey() + "\n- " + pair.getValue() + "\n\n");
      it.remove();
    }

    JScrollPane jp = new JScrollPane(contents);
    this.add(jp);
  }
}
