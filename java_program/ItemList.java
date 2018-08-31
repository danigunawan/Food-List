import org.json.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.lang.StringBuilder;
import java.util.Map;
import java.util.HashMap;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import java.awt.event.*;

public class ItemList extends JFrame implements ActionListener {
  Map<String, String> items;
  JPanel displayed_action_pane;
  ItemDisplay displayed_contents;

  public ItemList() {
    items = new HashMap<>();
    String jsonStringRecipes = fetch();
    extractRecipesFromJSON(jsonStringRecipes);
    frameSetup();
  }

  public void extractRecipesFromJSON(String recipes) {
    try {
      JSONArray json = new JSONArray(recipes);
      for(int i=0; i < json.length(); i++) {
        JSONObject obj = json.getJSONObject(i);
        String name = obj.getString("name");
        String description = obj.getString("description");
        items.put(name, description);
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }

  public String fetch() {
    StringBuilder sb = new StringBuilder();
    try {
      URL recipe_list_url = new URL("http://localhost:8080/recipes");
      HttpURLConnection connection = (HttpURLConnection) recipe_list_url.openConnection();
      connection.setRequestProperty("User-Agent", "Mozilla/5.0");
      connection.setRequestMethod("GET");

      BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      String inputLine;
      StringBuffer response = new StringBuffer();

      while ((inputLine = in.readLine()) != null) {
        sb.append(inputLine);
      }
      in.close();
    } catch(IOException e) {
      e.printStackTrace();
    }

    return sb.toString();
  }

  public void addComponents() {
    displayed_contents = new ItemDisplay(items);
    displayed_action_pane = new JPanel();

    displayed_action_pane.setBorder(new EtchedBorder());
    JButton fetch_button = new JButton("Fetch");

    fetch_button.addActionListener(this);
    displayed_action_pane.add(fetch_button);

    GridBagConstraints display_constraints = new GridBagConstraints(0, 0, 1, 1, 1, 0.95, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH,new Insets(0,0,0,0), 1, 1);
    GridBagConstraints action_constraints = new GridBagConstraints(0, 1, 1, 1, 1, 0.05, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH,new Insets(0,0,0,0), 0, 0);

    getContentPane().add(displayed_contents, display_constraints);
    getContentPane().add(displayed_action_pane, action_constraints);
  }

  public void frameSetup() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setPreferredSize(new Dimension(400, 500));
    setTitle("Food List");
    getContentPane().setLayout(new GridBagLayout());
    addComponents();
    setLocationRelativeTo(null);
    setVisible(true);
    pack();
  }

  public void actionPerformed(ActionEvent e) {
    String jsonStringRecipes = fetch();
    extractRecipesFromJSON(jsonStringRecipes);
    this.getContentPane().removeAll();
    addComponents();
    this.revalidate();
  }
}
