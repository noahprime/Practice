import requests

careerStats = 'http://stats.nba.com/stats/playercareerstats?LeagueID=00&PerMode=PerGame&PlayerID=1938'

response = request.get(careerStats)
response.raise_for_status()
perGame = response.json()['resultSets'][0]['rowSet']
