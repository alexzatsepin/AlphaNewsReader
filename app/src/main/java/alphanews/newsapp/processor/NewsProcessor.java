package alphanews.newsapp.processor;

import java.util.List;

import alphanews.newsapp.domain.News;
import alphanews.newsapp.server.commands.Command;

/**
 * Created by Zatsepin on 14.08.2014.
 */
class NewsProcessor {

    private final Command mCommand;
    private final Parser mParser;

    NewsProcessor(Command mCommand, Parser mParser) {
        this.mCommand = mCommand;
        this.mParser = mParser;
    }

    public List<News> process(){
       String response = mCommand.execute();
        try {
            return mParser.parse(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
