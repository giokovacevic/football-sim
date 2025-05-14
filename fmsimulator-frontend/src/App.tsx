import React from 'react';
import logo from './logo.svg';
import './App.css';
import { useState, useEffect } from 'react';
import Test from './practice_components/Test';
import { Country } from './model/country/Country';
import { Player } from './model/player/Player';
import { Club } from './model/team/Club';
import { League } from './model/competition/League';
import Positions from './components/position/Positions';
import Data from './components/data/Data';

// 

function App() {
  const [playersFixed, setPlayersFixed] = useState<Player[]>([]);
  const [players, setPlayers] = useState<Player[]>([]);
  const [currentLeague, setCurrentLeague] = useState<League | undefined>(undefined);

  useEffect(() => {
  }, []);

  const fetchPlayers = async () => {
    try {
      const response = await fetch('http://localhost:8080/api/football/players/all');
      const data:Player[] = await response.json();
      console.log(data);
      setPlayersFixed(data);
      setPlayers([...data]);
    } catch (error) {
      console.log(error);
    }
  }


  const handleOnDropdownMenuSelect = (item:League):void => {
    setCurrentLeague(item);
  }


  return (
    <>
      <Data></Data>
    </>
  );
}

export default App;
/*
<div>
        { leagues
        ? ( leagues.map((item, index) => 
        <div key={item.id}>
          <div>{index+1 + '.'}<LeagueTable league={item}></LeagueTable></div>
        </div>) )
        : ( <div>Nije Ucitano</div> )
        } 
      </div>
*/