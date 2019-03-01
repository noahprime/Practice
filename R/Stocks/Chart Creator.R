getSymbols("CTRL",src="yahoo")

chartSeries(CTRL, theme = chartTheme('white'),type = c("auto", "candlesticks", "matchsticks", "bars","line"),TA=c(addBBands(),addCCI(),addMACD(),addRSI(),addSMA(n=119),addSMA(n=20)),subset = 'last 8 months')
