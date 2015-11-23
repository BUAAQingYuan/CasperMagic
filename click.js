var  casper= require('casper').create();
var  url=casper.cli.get(0);
var  charset=casper.cli.get('charset');
var  isaction=casper.cli.get('action');
phantom.outputEncoding=charset;
casper.start(url);
if(Boolean(isaction))
{
    casper.then(function() {
         this.click('div.article-content p a');
    });
}
casper.then(function() {
      this.echo(this.getPageContent());
});
casper.run();
