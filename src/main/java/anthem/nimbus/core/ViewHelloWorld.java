package anthem.nimbus.core;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter; 
@Domain(value = "helloworld", includeListeners={ListenerType.websocket}) 
@Getter 
@Setter 
public class ViewHelloWorld { 
	@Page(route="", defaultPage=true) 
	private Page_HelloWorld helloWorldPage; 
	
	@Model 
	@Getter 
	@Setter 
	public static class Page_HelloWorld { 
		@Tile(title="Hello World Sample", size=Tile.Size.Medium) 
		private Card_HelloWorld helloWorldCard; }
}

@Model 
@Getter 
@Setter 
public static class Card_HelloWorld { 
	@Section 
	private Section_HelloWorld helloWorldSec; 
	} 

@Model 
@Getter 
@Setter 
public static class Section_HelloWorld { 
	@TextBox() 
	private String output; 
	@TextBox(postEventOnChange=true) 
	@NotNull 
	private String type; 
	}


