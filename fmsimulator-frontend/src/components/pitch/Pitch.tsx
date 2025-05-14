import { Role } from "../../model/player/Role";
import { Team } from "../../model/team/Team";
import "./pitch_style.css";
import PitchMember from "./PitchMember";

function Pitch({team, kits}:{team: Team, kits: string}) {

    return (
        <div className="pitch">
            {team.roster.lineup.formationDTO.requiredRoleDTOs.map((role:Role, index:number) => {
                const starter = team.roster.lineup.starters[role.stringValue];
                return (
                    <PitchMember key={role.stringValue} _role={role} starter={starter} teamId={team.id} kits={kits}></PitchMember>
                );
            })}

        </div>
    );
}
export default Pitch;