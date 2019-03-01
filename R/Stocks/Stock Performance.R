stocks <- read.table("SRT.txt",header = T,sep = "");
attach(stocks);

ggplot(stocks,aes(AV,PE)) + 
	geom_boxplot(aes(group = cut_width(AV,10000000)),outlier.color = "red",fill = "white",color = "blue")

#Boxplot of curent P/E for stock w certain AVG Volume ranges
curVal <- seq();
AV <- seq();
i <- 1
for(x in 1:length(stocks[1,]))
{
	if(stocks[1,x] >= 1000000){
		curVal[i] <- stocks[2,x];
		AV[i] <- stocks[1,x];
		i <- i + 1;
	}
}
curVal <- sort(curVal);
data <- data.frame(curVal);
ggplot(data=data,aes(AV,curVal)) + 
	geom_boxplot(aes(group = cut_width(AV,100000000)),outlier.color = "red",varwidth = TRUE,fill = "blue")

#histogram
ggplot(data=data,aes(curVal)) + 
	geom_histogram(binwidth = 5,alpha = .75) + 
	labs(title = "count vs P/E",x = "P/E",y = "count") +
	geom_hline(yintercept=0,size=0.3,color="black")


hist(curVal,breaks = (curVal[length(curVal)]-curVal[1])/5,col = "grey",xlab = "P/E",ylab = "count",main = "count vs P/E");