import { useEffect, useState } from 'react';
import type { Club } from './model/team/Club';
import PlayerList from './components/roster/PlayerList';
import type { Player } from './model/player/Player';
import Flag from './components/flag/Flag';

function App() {

  const [clubs, setClubs] = useState<Club[]>([]);
  const [players, setPlayers] = useState<Player[]>([]);

  useEffect(() => {
    fetchClubs();
  }, []);

  const fetchClubs = async () => {
    try {
      const response = await fetch('http://localhost:8080/api/football/clubs/all');
      const data:Club[] = await response.json();
      setClubs(data);
      updatePlayers(data[56]);
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
      <div style={{width: '800px'}}>
        {(clubs.length && players.length)
        ? 
        <PlayerList players={players} variant='lineup' includeHeader={true}></PlayerList>
        : 
        null
        }
      </div>
    </>
  )
}

export default App