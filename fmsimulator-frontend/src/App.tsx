import { useEffect, useState } from 'react';
import type { Club } from './model/team/Club';
import PlayerList from './components/roster/PlayerList';
import type { Player } from './model/player/Player';
import Flag from './components/flag/Flag';
import Data from './components/data/Data';

function App() {

  const [clubs, setClubs] = useState<Club[]>([]);
  const [players, setPlayers] = useState<Player[]>([]);

  useEffect(() => {
    fetchPlayers();
    //fetchClubs();
  }, []);

  const fetchPlayers = async () => {
    try {
      const response = await fetch('http://localhost:8080/api/football/players/all');
      const data:Player[] = await response.json();
      setPlayers(data);
    } catch (error) {
      console.log(error);
    }
  }

  const fetchClubs = async () => {
    try {
      const response = await fetch('http://localhost:8080/api/football/clubs/all');
      const data:Club[] = await response.json();
      setClubs(data);
      updatePlayers(data[33]);
    } catch (error) {
      console.log(error);
    }
  }

  const updatePlayers = (club:Club) => {
    const benchPlayers:Player[] = [...club.roster.bench];
    const startingPlayers:Player[] = [];
    club.roster.lineup.formationDTO.requiredRoleDTOs.forEach((role) => {
      if(!club.roster.lineup.starters[role.stringValue].empty) {
        startingPlayers.push(club.roster.lineup.starters[role.stringValue].player);
      }
    });
    setPlayers([...benchPlayers, ...startingPlayers]);
  }

  return (
    <>
      <div>
        <Data></Data>
      </div>
    </>
  )
}

export default App