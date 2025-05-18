import { useEffect, useRef, useState } from 'react';
import type { Player } from '../../model/player/Player';
import styles from './PlayerList.module.css';
import PlayerListHeader from './PlayerListHeader';
import PlayerListMember from './PlayerListMember';
import { compare } from '../../model/player/PlayerComparator';

export type PlayerListVariant = 'lineup' | 'squad' | 'global';

type PlayerListProps = {
    players: Player[];
    variant: PlayerListVariant;
    includeHeader: boolean;
};

type SortingOrder = 'desc' | 'asc';

const PlayerList = ({players, variant, includeHeader}:PlayerListProps) => {
    const [sortedPlayers, setSortedPlayers] = useState<Player[]>([...players]);
    const lastSortingKey = useRef<keyof Player>('rating');
    const lastSortingOrder = useRef<SortingOrder>('desc');

    useEffect(() => {
        sortPlayers(lastSortingKey.current, lastSortingOrder.current);
    }, [players]);

    const handleHeaderOnClick = (sortingKey:(keyof Player), orientation?:string) => {
        if(lastSortingKey.current === sortingKey && sortingKey !== 'preferredPositions') {
            if(lastSortingOrder.current === 'desc') {
                sortPlayers(sortingKey, 'asc', orientation);
            }else{
                sortPlayers(sortingKey, 'desc', orientation);
            }
        }else{
            sortPlayers(sortingKey, 'asc', orientation);
        }
    }

    const sortPlayers = (sortingKey:(keyof Player), sortingOrder:SortingOrder, orientation?:string) => {
        const sorted = [...players].sort((a, b) => {
            const result = compare(a, b, sortingKey, orientation);
            return sortingOrder === 'asc' ? result : -result;
        });
        setSortedPlayers(sorted);
        lastSortingKey.current = sortingKey;
        lastSortingOrder.current = sortingOrder;
    }

    return (
        <div className={styles.playerlist_root}>
            {includeHeader && (<PlayerListHeader onHeaderClicked={handleHeaderOnClick} variant={variant}></PlayerListHeader>)}
            {sortedPlayers.map((player) => <PlayerListMember key={player.id} player={player} variant={variant}></PlayerListMember>)}
        </div>
    );
}

export default PlayerList;